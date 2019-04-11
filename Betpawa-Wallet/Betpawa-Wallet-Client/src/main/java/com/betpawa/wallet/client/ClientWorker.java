/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betpawa.wallet.client;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.Channel;
import io.grpc.Status;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import com.betpawa.wallet.BalanceRequest;
import com.betpawa.wallet.BalanceResponse;
import com.betpawa.wallet.Currency;
import com.betpawa.wallet.TransactionRequest;
import com.betpawa.wallet.TransactionResponse;
import com.betpawa.wallet.WalletServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vingupta3
 */
public class ClientWorker {

    private final Logger logger = LoggerFactory.getLogger(ClientWorker.class);

    private final Channel channel;
    private final long userId;

    private final AtomicLong rpcCount = new AtomicLong();
    private final Semaphore limiter = new Semaphore(100);

    public ClientWorker(Channel channel, long userId) {
        this.channel = channel;
        this.userId = userId;
    }

    public long getRpcCount() {
        return rpcCount.get();
    }

    /**
     * Does the client work until {@code done.get()} returns true. Callers
     * should set done to true, and wait for this method to return.
     */
    void doClientWork(AtomicBoolean done) throws InterruptedException {
        Random random = new Random();
        WalletServiceGrpc.WalletServiceFutureStub stub = WalletServiceGrpc.newFutureStub(channel);
        AtomicReference<Throwable> errors = new AtomicReference<>();

        while (!done.get() && errors.get() == null) {
            // Pick a random ROUND to execute.
            int command = random.nextInt(3);

            if (command == 0) {
                doRoundA(stub, errors);
                continue;
            } else if (command == 1) {
                doRoundB(stub, errors);
            } else if (command == 2) {
                doRoundC(stub, errors);
            } else {
                throw new AssertionError();
            }
        }

        if (errors.get() != null) {
            throw new RuntimeException(errors.get());
        }
    }

    private void doRoundA(WalletServiceGrpc.WalletServiceFutureStub stub, AtomicReference<Throwable> errors) throws InterruptedException {
        // Deposit 100 USD
        deposit(stub, errors, userId, Currency.USD, 100);

        // Withdraw 200 USD
        withdraw(stub, errors, userId, Currency.USD, 200);

        // Deposit 100 EUR
        deposit(stub, errors, userId, Currency.EUR, 100);

        // Get Balance
        balance(stub, errors, userId);

        // Withdraw 100 USD
        withdraw(stub, errors, userId, Currency.USD, 100);

        // Get Balance
        balance(stub, errors, userId);

        // Withdraw 100 USD
        withdraw(stub, errors, userId, Currency.USD, 100);
    }

    private void doRoundB(WalletServiceGrpc.WalletServiceFutureStub stub, AtomicReference<Throwable> errors) throws InterruptedException {
        // Withdraw 100 GBP
        withdraw(stub, errors, userId, Currency.GBP, 100);

        // Deposit 300 GPB
        deposit(stub, errors, userId, Currency.GBP, 300);

        // Withdraw 100 GBP
        withdraw(stub, errors, userId, Currency.GBP, 100);

        // Withdraw 100 GBP
        withdraw(stub, errors, userId, Currency.GBP, 100);

        // Withdraw 100 GBP
        withdraw(stub, errors, userId, Currency.GBP, 100);
    }

    private void doRoundC(WalletServiceGrpc.WalletServiceFutureStub stub, AtomicReference<Throwable> errors) throws InterruptedException {
        // Get Balance
        balance(stub, errors, userId);

        // Deposit 100 USD
        deposit(stub, errors, userId, Currency.USD, 100);

        // Deposit 100 USD
        deposit(stub, errors, userId, Currency.USD, 100);

        // Withdraw 100 USD
        withdraw(stub, errors, userId, Currency.USD, 100);

        // Deposit 100 USD
        deposit(stub, errors, userId, Currency.USD, 100);

        // Get Balance
        balance(stub, errors, userId);

        // Withdraw 200 USD
        withdraw(stub, errors, userId, Currency.USD, 200);

        // Get Balance
        balance(stub, errors, userId);
    }

    public void balance(WalletServiceGrpc.WalletServiceFutureStub stub, AtomicReference<Throwable> errors,
            Long userId) throws InterruptedException {
        limiter.acquire();

        BalanceRequest balanceRequest = BalanceRequest.newBuilder()
                .setUserId(userId)
                .build();

        ListenableFuture<BalanceResponse> res = stub.balance(balanceRequest);

        res.addListener(() -> {
            rpcCount.incrementAndGet();
            limiter.release();
        }, MoreExecutors.directExecutor());

        Futures.addCallback(res, new FutureCallback<BalanceResponse>() {

            @Override
            public void onSuccess(BalanceResponse result) {
                if (result.getBalanceByCurrencyCount() < 1) {
                    errors.compareAndSet(null, new RuntimeException("Invalid response"));
                }

                double balanceUSD = result.getBalanceByCurrencyOrThrow(Currency.USD.name());
                logger.info("User [" + userId + "]: USD balance " + balanceUSD);

                double balanceEUR = result.getBalanceByCurrencyOrThrow(Currency.EUR.name());
                logger.info("User [" + userId + "]: EUR balance " + balanceEUR);

                double balanceGBP = result.getBalanceByCurrencyOrThrow(Currency.GBP.name());
                logger.info("User [" + userId + "]: GBP balance " + balanceGBP);
            }

            @Override
            public void onFailure(Throwable t) {
                if (Status.fromThrowable(t) == Status.NOT_FOUND) {
                    logger.error(t.getMessage());
                } else {
                    errors.compareAndSet(null, t);
                }
            }
        }, MoreExecutors.directExecutor());
    }

    public void withdraw(WalletServiceGrpc.WalletServiceFutureStub stub, AtomicReference<Throwable> errors,
            Long userId, Currency currency, double amount) throws InterruptedException {
        limiter.acquire();

        TransactionRequest transactionRequest = TransactionRequest.newBuilder()
                .setUserId(userId)
                .setAmount(amount)
                .setCurrency(currency)
                .build();

        ListenableFuture<TransactionResponse> res = stub.withdraw(transactionRequest);
        
        res.addListener(() -> {
            rpcCount.incrementAndGet();
            limiter.release();
        }, MoreExecutors.directExecutor());
        
        Futures.addCallback(res, new FutureCallback<TransactionResponse>() {
        
            @Override
            public void onSuccess(TransactionResponse result) {
                if (!result.equals(TransactionResponse.getDefaultInstance())) {
                    errors.compareAndSet(null, new RuntimeException("Invalid response"));
                }
                logger.info("User [" + userId + "]: Withdraw " + amount + " " + currency.name());
            }

            @Override
            public void onFailure(Throwable t) {
                Status[] statuses = {Status.NOT_FOUND, Status.INVALID_ARGUMENT, Status.INTERNAL};
                
                if (Arrays.stream(statuses).anyMatch(Status.fromThrowable(t)::equals)) {
                    logger.error(t.getMessage());
                } else {
                    errors.compareAndSet(null, t);
                }
            }
        }, MoreExecutors.directExecutor());
    }

    public void deposit(WalletServiceGrpc.WalletServiceFutureStub stub, AtomicReference<Throwable> errors,
            Long userId, Currency currency, double amount) throws InterruptedException {
        limiter.acquire();

        TransactionRequest transactionRequest = TransactionRequest.newBuilder()
                .setUserId(userId)
                .setAmount(amount)
                .setCurrency(currency)
                .build();

        ListenableFuture<TransactionResponse> res = stub.deposit(transactionRequest);
        
        res.addListener(() -> {
            rpcCount.incrementAndGet();
            limiter.release();
        }, MoreExecutors.directExecutor());
        
        Futures.addCallback(res, new FutureCallback<TransactionResponse>() {
        
            @Override
            public void onSuccess(TransactionResponse result) {
                if (!result.equals(TransactionResponse.getDefaultInstance())) {
                    errors.compareAndSet(null, new RuntimeException("Invalid response"));
                }
                logger.info("User [" + userId + "]: Deposit " + amount + " " + currency.name());
            }

            @Override
            public void onFailure(Throwable t) {
                Status[] statuses = {Status.NOT_FOUND, Status.INVALID_ARGUMENT, Status.INTERNAL};
                
                if (Arrays.stream(statuses).anyMatch(Status.fromThrowable(t)::equals)) {
                    logger.error(t.getMessage());
                } else {
                    errors.compareAndSet(null, t);
                }
            }
        }, MoreExecutors.directExecutor());
    }
}
