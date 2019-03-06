package com.example.grpc.client;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.HelloRequest;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


public class MyGrpcClient {

    public static void main(String [] args) throws InterruptedException{

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .useTransportSecurity()
                .build();

        // Create Channel with stub
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        String arbitraryMessage = "{A message from a server}";
        byte[] messageBytes = arbitraryMessage.getBytes();
        new String(messageBytes);

        // Build the services and set the values
        HelloRequest response =
        HelloRequest.newBuilder()
                .setPayload(ByteString.copyFrom("random Message".getBytes()))
                .putMetadataFields("id", "abc123")
                .build();

        //Print values to server
        System.out.println(response);

    }
}
