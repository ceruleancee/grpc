package com.example.grpc.server;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.HelloRequest;
import com.example.grpc.HelloResponse;
import io.grpc.internal.Stream;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    // Implement and build the greetings service found in .proto
    public void greeting(HelloRequest request, StreamObserver<HelloResponse> responseObserver){

        responseObserver.onNext(HelloResponse.newBuilder()
                // Send message to client
                .setGreeting("Payload " + request.getPayload())
                .setGreeting("PayloadType " + request.getPayloadType())
                .setGreeting("Metdata " + request.getMetadataFieldsMap())
                .build());
        responseObserver.onCompleted();

        //Print request to client
        System.out.println("Greeting Service Implemented "+ request);
    }


}



