/*
 * gRPC client that consumes the WalletService (see WalletService.proto) service
 */
package com.betpawa.wallet.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vingupta3
 */
public class WalletClient {
    
    private static final Logger LOG = LoggerFactory.getLogger(WalletClient.class);
    private static final long DURATION_SECONDS = 60;

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8980).usePlaintext().build();
        long userId = 11111;

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        try {
            AtomicBoolean done = new AtomicBoolean();
            
            ClientWorker worker = new ClientWorker(channel, userId);
            
            LOG.info("Starting");
            
            scheduler.schedule(() -> done.set(true), DURATION_SECONDS, TimeUnit.SECONDS);
            
            worker.doClientWork(done);
            
            double qps = (double) worker.getRpcCount() / DURATION_SECONDS;
            
            LOG.info("Did {0} RPCs/s", new Object[]{qps});
        } finally {
            scheduler.shutdownNow();
            channel.shutdownNow();
        }
    }
}
