package com.example.grpc;


import io.grpc.stub.StreamObserver;

import java.util.LinkedHashSet;
import java.util.LinkedList;

class GRPCMessageServiceImplU extends MessageServiceUGrpc.MessageServiceUImplBase {

    // Implement and build the MessageServiceU service found in .proto
    public void messageRequestHandlerU(MessageRequest messageRequest, StreamObserver<MessageResponse> responseObserver) {

        responseObserver.onNext(MessageResponse.newBuilder()
                // Send message to client
                .setMessage("Message")
                .build());
        responseObserver.onCompleted();

        //Print request to client
        System.out.println("Greeting Service Implemented " + messageRequest);
    }


}





