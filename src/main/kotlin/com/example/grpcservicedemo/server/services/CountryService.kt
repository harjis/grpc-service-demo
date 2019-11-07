package com.example.grpcservicedemo.server.services

import com.example.grpcservicedemo.grpc.Country
import com.example.grpcservicedemo.grpc.CountryServiceGrpc
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class CountryService : CountryServiceGrpc.CountryServiceImplBase() {
    companion object {
        val COUNTRIES = mapOf(
                Pair("A", "Austria"),
                Pair("B", "Brasil"),
                Pair("C", "Chile"),
                Pair("D", "Danmakr"),
                Pair("E", "Estonia"),
                Pair("F", "Finlanddi")
        )
    }

    override fun getCountries(request: Country.CountryRequest?, responseObserver: StreamObserver<Country.CountriesResponse>?) {
        val ids = request?.idList
        val responseBuilder = Country.CountriesResponse.newBuilder()
        ids?.forEach { responseBuilder.addCountry(COUNTRIES[it]) }

        responseObserver?.onNext(responseBuilder.build())
        responseObserver?.onCompleted()
    }
}
