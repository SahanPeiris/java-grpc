package com.github.sahan.grpc.greeting.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello gRPC");

        // create server on a thread
        Server server = ServerBuilder.forPort(50051)
                .addService(new GreetServiceImpl())
                .build();

        //start the server
        server.start();

        //handle a shutdown properly
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received shutdown Request");
            server.shutdown();
            System.out.println("Successfully shutdown the Server");

        }));


        server.awaitTermination();



    }
}

