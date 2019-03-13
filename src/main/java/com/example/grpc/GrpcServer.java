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
}
