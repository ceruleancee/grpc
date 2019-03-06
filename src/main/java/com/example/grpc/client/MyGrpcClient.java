package com.example.grpc.client;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.HelloRequest;
import com.example.grpc.PayloadType;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


public class MyGrpcClient {

    public static void main(String [] args) throws InterruptedException{

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .useTransportSecurity()
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        String graylogMessage = "{A message from a server}";
        byte[] messageBytes = graylogMessage.getBytes();

        new String(messageBytes);

        // Build the services defined in proto
        HelloRequest response =
        HelloRequest.newBuilder()
                .setPayloadType(PayloadType.GELF)
                .setPayload(ByteString.copyFrom("random Message".getBytes()))
                .putMetadataFields("cluster_id", "abc123")
                .build();

        //Print values to server
        System.out.println(response);

    }
}
