/*
 * Author: CeruleanCee
 */
package com.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;

import javax.net.ssl.SSLException;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class GrpcClient {

    public static void main(String[] args) throws InterruptedException, SSLException {

        File certChainFile = new File("/PATH/TO/certificate.pem");

        // Create a channel
        ManagedChannel channel = NettyChannelBuilder.forAddress("localhost", 8443)
                .sslContext(GrpcSslContexts
                        .forClient()
                        .trustManager(certChainFile)
                        .build())
                // .nameResolverFactory() // For service registry
                // .loadBalancerFactory() // Client side load balancer
                .build();

        MessageRequest messageRequest = MessageRequest.newBuilder()
                .setMessage("This is an arbitrary message!")
                .build();

        // Create Unary Synchronous Blocking Stub
        MessageServiceUGrpc.MessageServiceUBlockingStub uBlockingStub = MessageServiceUGrpc.newBlockingStub(channel);
        uBlockingStub.messageServiceHandlerU(messageRequest);

        // Bidirectional Synchronous Blocking Stub
        MessageServiceBGrpc.MessageServiceBStub bStub = MessageServiceBGrpc.newStub(channel);
        StreamObserver<MessageRequest> clientSideRequestStream = bStub.messageServiceHandlerB(buildResponseHandler());
        for (int i = 0; i < 10000; i++) {
            System.out.println(i + ": " + messageRequest);
            clientSideRequestStream.onNext(messageRequest);
        }
        channel.awaitTermination(10000, TimeUnit.DAYS);
    }

    private static StreamObserver<MessageResponse> buildResponseHandler() {
        return new StreamObserver<MessageResponse>() {
            @Override
            public void onNext(MessageResponse messageResponse) {
                System.out.println("Response received: " + messageResponse);
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onCompleted() {
            }
        };
    }
}
