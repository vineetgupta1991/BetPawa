package com.betpawa.wallet.server;

import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import java.math.BigDecimal;
import com.betpawa.wallet.Currency;

import org.junit.*;
import org.junit.rules.ExpectedException;

import com.betpawa.wallet.server.dao.AccountDAO;
import com.betpawa.wallet.server.dao.AccountDAOImpl;
import com.betpawa.wallet.server.model.Account;

/**
 * @author vingupta3
 */
public class WalletServerTest {

    /**
     * This rule manages automatic graceful shutdown for the registered channel at the end of test.
     */
//    @Rule
    @ClassRule
    public final static GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    private static WalletServer server;
    protected static ManagedChannel inProcessChannel;
    
    private final static AccountDAO accountDAO = new AccountDAOImpl();
    protected final static long userId = 1L;

    @BeforeClass
    public static void setUpClass() throws Exception {
        // Generate a unique in-process server name.
        String serverName = InProcessServerBuilder.generateName();
        // Use directExecutor for both InProcessServerBuilder and InProcessChannelBuilder can reduce the
        // usage timeouts and latches in test. But we still add timeout and latches where they would be
        // needed if no directExecutor were used, just for demo purpose.
        server = new WalletServer(InProcessServerBuilder.forName(serverName).directExecutor(), 0);
        server.start();
        // Create a client channel and register for automatic graceful shutdown.
        inProcessChannel = grpcCleanup.register(
                InProcessChannelBuilder.forName(serverName).directExecutor().build());
        
        // USD account
        Account usd = accountDAO.saveOrUpdate(new Account(userId, Currency.USD.name(), BigDecimal.ZERO));
        // EUR account
        Account eur = accountDAO.saveOrUpdate(new Account(userId, Currency.EUR.name(), BigDecimal.ZERO));
        // GBP account
        Account gbp = accountDAO.saveOrUpdate(new Account(userId, Currency.GBP.name(), BigDecimal.ZERO));
    }

    @AfterClass
    public static void tearDownClass() {
        int num = accountDAO.deleteByUserId(userId);
        System.out.println(num + " Accounts for User " + userId + " were deleted");
        
        server.stop();
    }
}