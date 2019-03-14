/*
 * Author: CeruleanCee
 */
package com.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

public class GrpcClient {

    public static void main(String[] args) throws InterruptedException {

        // Create a channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8443)

                // .useTransportSecurity() // Security option
                // .nameResolverFactory() // For service registry
                // .loadBalancerFactory() // Client side load balancer
                .usePlaintext(true)
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
            System.out.println(i + " : " + messageRequest);
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
