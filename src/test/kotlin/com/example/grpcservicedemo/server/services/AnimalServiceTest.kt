package com.example.grpcservicedemo.server.services

import com.example.grpcservicedemo.grpc.AnimalOuterClass
import com.example.grpcservicedemo.grpc.AnimalServiceGrpc
import io.grpc.ManagedChannelBuilder
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AnimalServiceTest {
    @Autowired
    lateinit var gRpcServerProperties: GRpcServerProperties

    @Test
    fun returnsAllAnimals_whenNoIdSet() {
        val channel = ManagedChannelBuilder
                .forAddress("localhost", gRpcServerProperties.runningPort)
                .usePlaintext()
                .build()
        val animalStub = AnimalServiceGrpc.newFutureStub(channel)
        val request = AnimalOuterClass.AnimalRequest.newBuilder().build()
        val response = animalStub.getAnimals(request).get().animalList
        Assertions.assertThat(response.size).isEqualTo(2)
    }

    @Test
    fun returnsSpecificAnimal_whenIdIsGivven() {
        val channel = ManagedChannelBuilder
                .forAddress("localhost", gRpcServerProperties.runningPort)
                .usePlaintext()
                .build()
        val animalStub = AnimalServiceGrpc.newFutureStub(channel)
        val request = AnimalOuterClass.AnimalRequest.newBuilder().setId("0").build()
        val response = animalStub.getAnimals(request).get().animalList
        Assertions.assertThat(response.size).isEqualTo(1)
    }
}
