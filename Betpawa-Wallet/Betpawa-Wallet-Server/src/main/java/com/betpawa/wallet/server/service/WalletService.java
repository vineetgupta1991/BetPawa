/*
 * Implementation of WalletService
 */
package com.betpawa.wallet.server.service;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import com.betpawa.wallet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.betpawa.wallet.server.dao.AccountDAO;
import com.betpawa.wallet.server.exception.AccountNotFoundException;
import com.betpawa.wallet.server.exception.NotValidAmountException;
import com.betpawa.wallet.server.exception.UnknownCurrencyException;
import com.betpawa.wallet.server.model.Account;
import com.betpawa.wallet.server.util.CurrencyUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author vingupta3
 */
public class WalletService extends WalletServiceGrpc.WalletServiceImplBase {

    private final Logger logger = LoggerFactory.getLogger(WalletService.class);

    private final AccountDAO accountDAO;

    public WalletService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void balance(BalanceRequest request, StreamObserver<BalanceResponse> responseObserver) {
        List<Account> accounts = accountDAO.findAllByUserId(request.getUserId());

        if (accounts.isEmpty()) {
            String msg = "There's no Accounts for User " + request.getUserId();
            logger.warn(msg);
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription(msg)
                    .withCause(new AccountNotFoundException(msg))
                    .asRuntimeException());
            return;
        }

        Map<String, Double> resultMap = accounts.stream().collect(
                Collectors.toMap(a -> a.getCurrencyCode(), a -> a.getBalance().doubleValue()));

        BalanceResponse response = BalanceResponse.newBuilder()
                .putAllBalanceByCurrency(resultMap)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void withdraw(TransactionRequest request, StreamObserver<TransactionResponse> responseObserver) {
        String currencyCode = request.getCurrency().name();

        try {
            CurrencyUtil.validateCurrencyCode(currencyCode);
        } catch (UnknownCurrencyException ex) {
            logger.error(ex.getMessage(), ex);

            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(ex.getMessage())
                    .augmentDescription("Unknown currency")
                    .withCause(ex)
                    .asRuntimeException());
            return;
        }

        BigDecimal amount = BigDecimal.valueOf(request.getAmount());

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            String msg = "Not valid amount";
            logger.error(msg);
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(msg)
                    .withCause(new NotValidAmountException(msg))
                    .asRuntimeException());
            return;
        }

        Optional<Account> account = accountDAO.findByUserIdAndCurrency(request.getUserId(), currencyCode);

        if (!account.isPresent()) {
            String msg = "There's no " + currencyCode + " Account for User " + request.getUserId();
            logger.warn(msg);
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription(msg)
                    .withCause(new AccountNotFoundException(msg))
                    .asRuntimeException());
            return;
        }

        Long accountId = account.get().getId();

        // withdraw
        try {
            BigDecimal delta = amount.negate();
            logger.debug("Withdraw service: delta " + delta + " Account ID = " + accountId);
            accountDAO.updateAccountBalance(accountId, delta);
        } catch (RuntimeException ex) {
            logger.error(ex.getMessage(), ex);

            responseObserver.onError(Status.INTERNAL
                    .withDescription(ex.getMessage())
                    .augmentDescription("Withdraw Transaction Failed")
                    .withCause(ex)
                    .asRuntimeException());
            return;
        }

        responseObserver.onNext(TransactionResponse.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void deposit(TransactionRequest request, StreamObserver<TransactionResponse> responseObserver) {
        String currencyCode = request.getCurrency().name();

        try {
            CurrencyUtil.validateCurrencyCode(currencyCode);
        } catch (UnknownCurrencyException ex) {
            logger.error(ex.getMessage(), ex);

            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(ex.getMessage())
                    .augmentDescription("Unknown currency")
                    .withCause(ex)
                    .asRuntimeException());
            return;
        }

        BigDecimal amount = BigDecimal.valueOf(request.getAmount());

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            String msg = "Not valid amount";
            logger.error(msg);
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(msg)
                    .withCause(new NotValidAmountException(msg))
                    .asRuntimeException());
            return;
        }

        Optional<Account> account = accountDAO.findByUserIdAndCurrency(request.getUserId(), currencyCode);

        if (!account.isPresent()) {
            String msg = "There's no " + currencyCode + " Account for User " + request.getUserId();
            logger.warn(msg);
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription(msg)
                    .withCause(new AccountNotFoundException(msg))
                    .asRuntimeException());
            return;
        }

        Long accountId = account.get().getId();

        // deposit
        try {
            logger.debug("Deposit service: amount " + amount + " Account ID = " + accountId);
            accountDAO.updateAccountBalance(accountId, amount);
        } catch (RuntimeException ex) {
            logger.error(ex.getMessage(), ex);

            responseObserver.onError(Status.INTERNAL
                    .withDescription(ex.getMessage())
                    .augmentDescription("Deposit Transaction Failed")
                    .withCause(ex)
                    .asRuntimeException());
            return;
        }

        responseObserver.onNext(TransactionResponse.getDefaultInstance());
        responseObserver.onCompleted();
    }
}
