/*
 * Author: CeruleanCee
 */
package com.example.grpc;

import io.grpc.stub.StreamObserver;

public abstract class GRPCMessageServiceImpl {


    // Unary Service Implemented
    public static class MessageServiceU extends MessageServiceUGrpc.MessageServiceUImplBase {

        // Implement and build the MessageServiceU service found in .proto
        public void messageServiceHandlerU(MessageRequest messageRequest, StreamObserver<MessageResponse> responseObserver) {

            responseObserver.onNext(MessageResponse.newBuilder()
                    // Send message to client
                    .setMessage(messageRequest.toString())
                    .build());
            responseObserver.onCompleted();

            //Print request to client
            System.out.println("Unary Message sent -> " + messageRequest);
        }
    }

    // Bidirectional Service Implemented
    public static class MessageServiceB extends MessageServiceBGrpc.MessageServiceBImplBase {

        // Implement and build the MessageServiceB service found in .proto


        public StreamObserver<MessageRequest> messageServiceHandlerB(StreamObserver<MessageResponse> responseObserver) {

            return new StreamObserver<MessageRequest>() {

                public void onNext(MessageRequest messageRequest) {

                    System.out.println(messageRequest.toString());
                    // Pass the messageRequest off to be processed by Graylog input system
                    MessageResponse messageResponse = MessageResponse.newBuilder()
                            .setMessage(messageRequest.toString())
                            .build();

                    // Will send response to the only connected client.
                    responseObserver.onNext(messageResponse);
                }

                public void onError(Throwable t) {

                }

                public void onCompleted() {

                }
            };
        }

    }

}

