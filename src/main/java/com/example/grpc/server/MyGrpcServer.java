package com.example.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class MyGrpcServer {

    static public void main(String [] args) throws IOException, InterruptedException{

        //Build the server instance
        Server server = ServerBuilder.forPort(8080)
                //add the rpc service
                .addService(new GreetingServiceImpl())
                .build();
        server.start();

        server.awaitTermination();
    }
}
