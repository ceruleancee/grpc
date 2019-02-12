package com.example.grpc.client;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.HelloRequest;
import com.example.grpc.HelloResponse;
import com.example.grpc.Status;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MyGrpcClient {
    public static void main(String [] args) throws InterruptedException{
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                //change this later
                .usePlaintext(true)
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        HelloResponse response = stub.greeting(HelloRequest.newBuilder()
                .setArbVariable("Arbitrary Value")
                .setStatus(Status.Good)
                .putBagOfVariables("TrickOne", "TrickTwo")
                .build());

        System.out.println(response);

        //TODO find out why MyGrpcClient has a runtime Error Exception



    }
}
