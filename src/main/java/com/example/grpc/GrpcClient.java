package com.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

    public static void main(String [] args) throws InterruptedException{

        // Create a channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8443)
                // .useTransportSecurity() // Security option
                // .nameResolverFactory() // For service registry
                // .loadBalancerFactory() // Client side load balancer
                .usePlaintext(true)
                .build();

        // Unary Synchronous Blocking Stub
        MessageServiceUGrpc.MessageServiceUBlockingStub uBlockingStub = MessageServiceUGrpc.newBlockingStub(channel);
        uBlockingStub.messageServiceHandlerU(MessageRequest.newBuilder()
                                                            .setMessage("Cerulean")
                                                            .build());

//        MessageServiceBGrpc.MessageServiceBBlockingStub bBlockingStub = MessageServiceBGrpc.newBlockingStub(channel);
//        bBlockingStub.messageServiceHandlerB(MessageRequest.newBuilder()
//                                                            .setMessage("CeruleanCee")
//                                                            .build());

    }
}
