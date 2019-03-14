/*
 * Author: CeruleanCee
 */
package com.example.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

    static public void main(String[] args) throws IOException, InterruptedException {

        //Build the server instance
        Server server = ServerBuilder.forPort(8443)
                //add the service defined in the proto file
                .addService(new GRPCMessageServiceImpl.MessageServiceU())   // Add unary service
                .addService(new GRPCMessageServiceImpl.MessageServiceB())  // Add bidirectional service
                .build();
        server.start();
        System.out.println("Server is listening...");

        // Something happens here

        server.awaitTermination();
    }


}
