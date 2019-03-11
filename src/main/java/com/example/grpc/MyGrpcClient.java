package com.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MyGrpcClient {

    public static void main(String [] args) throws InterruptedException{

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .useTransportSecurity()
                .build();

        // Create Channel with stub

        String arbitraryMessage = "{A message from a server}";
        byte[] messageBytes = arbitraryMessage.getBytes();
        new String(messageBytes);

        // Build the services and set the values

    }
}
