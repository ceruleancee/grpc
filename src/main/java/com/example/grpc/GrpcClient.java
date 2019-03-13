package com.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

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
                .setMessage("This is an arbitrary message! :P ")
                .build();

        // Create Unary Synchronous Blocking Stub
        MessageServiceUGrpc.MessageServiceUBlockingStub uBlockingStub = MessageServiceUGrpc.newBlockingStub(channel);
        uBlockingStub.messageServiceHandlerU(messageRequest);


        // Bidirectional Synchronous Blocking Stub
//        MessageServiceBGrpc.MessageServiceBBlockingStub bBlockingStub = MessageServiceBGrpc.newBlockingStub(channel);
//        bBlockingStub.messageServiceHandlerB(MessageRequest.newBuilder()
//                                                            .setMessage("CeruleanCee")
//                                                            .build());

    }
}
