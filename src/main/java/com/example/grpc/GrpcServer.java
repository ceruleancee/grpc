package com.example.grpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.LinkedHashSet;

public class GrpcServer {

    static public void main(String [] args) throws IOException, InterruptedException {

        //Build the server instance
        Server server = ServerBuilder.forPort(8443)
                //add the service defined in the proto file
                .addService(new GRPCMessageServiceImplU())  // Add unary service
                .addService(new GRPCMessageServiceImplB())  // Add bidirectiona service
                .build();
        server.start();
        server.awaitTermination();
    }

    // Unary Service Implemented
    static class GRPCMessageServiceImplU extends MessageServiceUGrpc.MessageServiceUImplBase {

        // Implement and build the MessageServiceU service found in .proto
        public void messageRequestHandlerU(MessageRequest messageRequest, StreamObserver<MessageResponse> responseObserver) {
            System.out.println("Hello World! " + messageRequest.getMessage());
            String message = "Hello World! " + messageRequest.getMessage();

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
    public static class GRPCMessageServiceImplB extends MessageServiceBGrpc.MessageServiceBImplBase {

        // Implement and build the MessageServiceU service found in .proto
         static LinkedHashSet<StreamObserver<MessageResponse>> observers = new LinkedHashSet<StreamObserver<MessageResponse>>();

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
}
