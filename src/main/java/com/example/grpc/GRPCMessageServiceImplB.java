package com.example.grpc;

import io.grpc.stub.StreamObserver;

import java.util.LinkedHashSet;

//Implement and build the MessageServiceB service found in .proto
public class GRPCMessageServiceImplB extends MessageServiceBGrpc.MessageServiceBImplBase {

    private static LinkedHashSet<StreamObserver<MessageResponse>> observers = new LinkedHashSet<StreamObserver<MessageResponse>>();

//    @Override
//    public GRPCMessageServiceImplB(MessageRequest messageRequest, StreamObserver<MessageResponse> responseObserver) {
//
//    }
    public StreamObserver<MessageRequest>MessageServiceHandlerB(StreamObserver<MessageResponse> responseObserver){
        observers.add(responseObserver);
        return  new StreamObserver<MessageRequest>() {

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
