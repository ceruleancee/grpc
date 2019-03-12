package com.example.grpc;


import io.grpc.stub.StreamObserver;

public class GRPCMessageServiceImplement extends MessageServiceUGrpc.MessageServiceUImplBase{

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

    //Implement and build the MessageServiceB service found in .proto

}



