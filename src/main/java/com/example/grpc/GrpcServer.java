package com.example.grpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

    static public void main(String [] args) throws IOException, InterruptedException{

        //Build the server instance
        Server server = ServerBuilder.forPort(8080)
                //add the service defined in the proto file
                //.addService(MessageServiceU)
                //.addService(MessageServiceB)
                .build();
        server.start();

        server.awaitTermination();
    }
}
