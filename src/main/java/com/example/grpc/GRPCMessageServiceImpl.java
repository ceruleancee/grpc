package com.example.grpc;

import io.grpc.stub.StreamObserver;

import java.util.LinkedHashSet;

public abstract class GRPCMessageServiceImpl {


    // Unary Service Implemented
    public static class MessageServiceU extends MessageServiceUGrpc.MessageServiceUImplBase {

        // Implement and build the MessageServiceU service found in .proto
        public void messageServiceHandlerU(MessageRequest messageRequest, StreamObserver<MessageResponse> responseObserver) {
            System.out.println( messageRequest.getMessage());
            String message = "This is MessageServiceU..." + messageRequest.getMessage();

            responseObserver.onNext(MessageResponse.newBuilder()
                    // Send message to client
                    .setMessage(messageRequest.toString())
                    .build());
            responseObserver.onCompleted();

            //Print request to client
            System.out.println("GRPCMessageServiceImplU works " + messageRequest);
        }
    }

    // Bidirectional Service Implemented
    public static class MessageServiceB extends MessageServiceBGrpc.MessageServiceBImplBase {

        // Implement and build the MessageServiceB service found in .proto
        static LinkedHashSet<StreamObserver<MessageResponse>> observers = new LinkedHashSet<>();

        public StreamObserver<MessageRequest> messageServiceHandlerB(StreamObserver<MessageResponse> responseObserver) {
            observers.add(responseObserver);
            return new StreamObserver<MessageRequest>() {

                public void onNext(MessageRequest messageRequest) {
                    MessageResponse messageResponse = MessageResponse.newBuilder()
                            .setMessage(messageRequest.toString())
                            .build();
                    observers.forEach(o -> o.onNext(messageResponse));
                }

                public void onError(Throwable t) {
                    observers.remove(responseObserver);
                }

                public void onCompleted() {
                    observers.remove(responseObserver);
                }
            };
        }

    }

}

