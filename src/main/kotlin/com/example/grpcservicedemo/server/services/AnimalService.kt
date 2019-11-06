package com.example.grpcservicedemo.server.services

import com.example.grpcservicedemo.grpc.AnimalOuterClass
import com.example.grpcservicedemo.grpc.AnimalServiceGrpc
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class AnimalService : AnimalServiceGrpc.AnimalServiceImplBase() {
    companion object {
        val ANIMALS = listOf(
                AnimalOuterClass.Animal.newBuilder()
                        .setName("Pulpo")
                        .setColor("pink")
                        .addAllCountry(listOf("A", "F"))
                        .build(),
                AnimalOuterClass.Animal.newBuilder()
                        .setName("Pölpö")
                        .setColor("pänk")
                        .addAllCountry(listOf("B", "C"))
                        .build()
        )
    }

    override fun getAnimals(request: AnimalOuterClass.AnimalRequest?, responseObserver: StreamObserver<AnimalOuterClass.AnimalsResponse>?) {
        val responseBuilder = AnimalOuterClass.AnimalsResponse.newBuilder()

        val animalId = request?.id
        if (animalId == null || animalId == "") {
            responseBuilder.addAllAnimal(ANIMALS)
        } else {
            responseBuilder.addAnimal(ANIMALS[Integer.parseInt(animalId)])
        }

        responseObserver?.onNext(responseBuilder.build())
        responseObserver?.onCompleted()
        println("getAnimals done for id: ${animalId}")
    }
}
