package com.example.grpc;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class MyGrpcServer {

    static public void main(String [] args) throws IOException, InterruptedException{

        //Build the server instance
        Server server = ServerBuilder.forPort(8080)
                //add the service defined in the proto file
                //.addService(true)
                .build();
        server.start();

        server.awaitTermination();
    }
}
