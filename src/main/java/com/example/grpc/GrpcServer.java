/*
 * Author: CeruleanCee
 */
package com.example.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.File;
import java.io.IOException;

public class GrpcServer {

    static public void main(String[] args) throws IOException, InterruptedException {

        File certChainFile = new File("/PATH/TO/certificate.pem");
        File privateKeyFile = new File("/PATH/TO/key.pem");

        //Build the server instance
        Server server = ServerBuilder.forPort(8443)
                //add the service defined in the proto file
                .useTransportSecurity(certChainFile, privateKeyFile)
                .addService(new GRPCMessageServiceImpl.MessageServiceU())   // Add unary service
                .addService(new GRPCMessageServiceImpl.MessageServiceB())  // Add bidirectional service
                .build();
        server.start();
        System.out.println("Server is listening...");

        // Something happens here

        server.awaitTermination();
    }


}
