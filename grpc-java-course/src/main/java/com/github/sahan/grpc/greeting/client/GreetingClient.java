package com.github.sahan.grpc.greeting.client;

import com.proto.dummy.Dummy;
import com.proto.dummy.DummyMessageOrBuilder;
import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {
    public static void main(String[] args) {
        System.out.println("Hello, I'm a gRPC client!");

        //transport channel that talks to the server
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        System.out.println("Creating stub");
        // old and dummy
//        DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);
//        DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.newFutureStub(channel);

        // create a greet service client that is Blocking / synchronous
        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);

        // created a protocol buffer greeting message
        Greeting greeting = Greeting.newBuilder()
                .setFirstName("Sahan")
                .setLastName("Peiris")
                .build();

        // created a protocol buffer greet request
        GreetRequest greetRequest = GreetRequest.newBuilder()
                .setGreeting(greeting)
                .build();

        // call the rpc and get back a response (protocol buffer)
        GreetResponse response = greetClient.greet(greetRequest);

        System.out.println(response.getResult());

        System.out.println("Shutting down channel");

        channel.shutdown();





    }
}
