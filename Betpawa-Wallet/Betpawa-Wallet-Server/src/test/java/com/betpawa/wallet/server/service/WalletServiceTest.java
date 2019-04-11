package com.betpawa.wallet.server.service;

import io.grpc.StatusRuntimeException;
import com.betpawa.wallet.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.betpawa.wallet.server.WalletServerTest;

/**
 * @author vingupta3
 */
@RunWith(JUnit4.class)
public class WalletServiceTest extends WalletServerTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testScenario1() {
        WalletServiceGrpc.WalletServiceBlockingStub stub = WalletServiceGrpc.newBlockingStub(inProcessChannel);

        // 1. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
        TransactionRequest transactionRequest = TransactionRequest.newBuilder()
                .setUserId(userId)
                .setAmount(200)
                .setCurrency(Currency.USD)
                .build();

        exceptionRule.expect(StatusRuntimeException.class);
        stub.withdraw(transactionRequest);
    }

    @Test
    public void testScenario2() {
        WalletServiceGrpc.WalletServiceBlockingStub stub = WalletServiceGrpc.newBlockingStub(inProcessChannel);

        // 2. Make a deposit of USD 100 to user with id 1.
        TransactionRequest transactionRequest = TransactionRequest.newBuilder()
                .setUserId(userId)
                .setAmount(100)
                .setCurrency(Currency.USD)
                .build();

        stub.deposit(transactionRequest);

        // 3. Check that all balances are correct
        BalanceRequest balanceRequest = BalanceRequest.newBuilder()
                .setUserId(userId)
                .build();

        BalanceResponse balanceResponse = stub.balance(balanceRequest);
        double balanceUSD = balanceResponse.getBalanceByCurrencyOrThrow(Currency.USD.name());
        Assert.assertEquals(100, balanceUSD, 1);
        double balanceEUR = balanceResponse.getBalanceByCurrencyOrThrow(Currency.EUR.name());
        Assert.assertEquals(0, balanceEUR, 1);
        double balanceGBP = balanceResponse.getBalanceByCurrencyOrThrow(Currency.GBP.name());
        Assert.assertEquals(0, balanceGBP, 1);
    }

    @Test
    public void testScenario3() {
        WalletServiceGrpc.WalletServiceBlockingStub stub = WalletServiceGrpc.newBlockingStub(inProcessChannel);

        // 4. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
        TransactionRequest transactionRequest = TransactionRequest.newBuilder()
                .setUserId(userId)
                .setAmount(200)
                .setCurrency(Currency.USD)
                .build();

        exceptionRule.expect(StatusRuntimeException.class);
        stub.withdraw(transactionRequest);
    }

    @Test
    public void testScenario4() {
        WalletServiceGrpc.WalletServiceBlockingStub stub = WalletServiceGrpc.newBlockingStub(inProcessChannel);

        // 5. Make a deposit of EUR 100 to user with id 1.
        TransactionRequest transactionRequest = TransactionRequest.newBuilder()
                .setUserId(userId)
                .setAmount(100)
                .setCurrency(Currency.EUR)
                .build();

        stub.deposit(transactionRequest);

        // 6. Check that all balances are correct
        BalanceRequest balanceRequest = BalanceRequest.newBuilder()
                .setUserId(userId)
                .build();

        BalanceResponse balanceResponse = stub.balance(balanceRequest);
        double balanceUSD = balanceResponse.getBalanceByCurrencyOrThrow(Currency.USD.name());
        Assert.assertEquals(100, balanceUSD, 1);
        double balanceEUR = balanceResponse.getBalanceByCurrencyOrThrow(Currency.EUR.name());
        Assert.assertEquals(100, balanceEUR, 1);
        double balanceGBP = balanceResponse.getBalanceByCurrencyOrThrow(Currency.GBP.name());
        Assert.assertEquals(0, balanceGBP, 1);
    }

    @Test
    public void testScenario5() {
        WalletServiceGrpc.WalletServiceBlockingStub stub = WalletServiceGrpc.newBlockingStub(inProcessChannel);

        // 7. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
        TransactionRequest transactionRequest = TransactionRequest.newBuilder()
                .setUserId(userId)
                .setAmount(200)
                .setCurrency(Currency.USD)
                .build();

        exceptionRule.expect(StatusRuntimeException.class);
        stub.withdraw(transactionRequest);
    }

    @Test
    public void testScenario6() {
        WalletServiceGrpc.WalletServiceBlockingStub stub = WalletServiceGrpc.newBlockingStub(inProcessChannel);
        
        // 8. Make a deposit of USD 100 to user with id 1.
        TransactionRequest transactionRequest = TransactionRequest.newBuilder()
                .setUserId(userId)
                .setAmount(100)
                .setCurrency(Currency.USD)
                .build();

        stub.deposit(transactionRequest);

        // 9. Check that all balances are correct
        BalanceRequest balanceRequest = BalanceRequest.newBuilder()
                .setUserId(userId)
                .build();

        BalanceResponse balanceResponse = stub.balance(balanceRequest);
        double balanceUSD = balanceResponse.getBalanceByCurrencyOrThrow(Currency.USD.name());
        Assert.assertEquals(200, balanceUSD, 1);
        double balanceEUR = balanceResponse.getBalanceByCurrencyOrThrow(Currency.EUR.name());
        Assert.assertEquals(100, balanceEUR, 1);
        double balanceGBP = balanceResponse.getBalanceByCurrencyOrThrow(Currency.GBP.name());
        Assert.assertEquals(0, balanceGBP, 1);
    }

    @Test
    public void testScenario7() {
        WalletServiceGrpc.WalletServiceBlockingStub stub = WalletServiceGrpc.newBlockingStub(inProcessChannel);

        // 10. Make a withdrawal of USD 200 for user with id 1. Must return "ok".
        TransactionRequest transactionRequest = TransactionRequest.newBuilder()
                .setUserId(userId)
                .setAmount(200)
                .setCurrency(Currency.USD)
                .build();

        stub.withdraw(transactionRequest);

        // 11. Check that all balances are correct
        BalanceRequest balanceRequest = BalanceRequest.newBuilder()
                .setUserId(userId)
                .build();

        BalanceResponse balanceResponse = stub.balance(balanceRequest);
        double balanceUSD = balanceResponse.getBalanceByCurrencyOrThrow(Currency.USD.name());
        Assert.assertEquals(0, balanceUSD, 1);
        double balanceEUR = balanceResponse.getBalanceByCurrencyOrThrow(Currency.EUR.name());
        Assert.assertEquals(100, balanceEUR, 1);
        double balanceGBP = balanceResponse.getBalanceByCurrencyOrThrow(Currency.GBP.name());
        Assert.assertEquals(0, balanceGBP, 1);
    }

    @Test
    public void testScenario8() {
        WalletServiceGrpc.WalletServiceBlockingStub stub = WalletServiceGrpc.newBlockingStub(inProcessChannel);

        // 12. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
        TransactionRequest transactionRequest = TransactionRequest.newBuilder()
                .setUserId(userId)
                .setAmount(200)
                .setCurrency(Currency.USD)
                .build();

        exceptionRule.expect(StatusRuntimeException.class);
        stub.withdraw(transactionRequest);
    }
}
