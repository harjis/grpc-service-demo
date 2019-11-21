package com.example.grpcservicedemo.server.services

import com.example.grpcservicedemo.grpc.CountryOuterClass
import com.example.grpcservicedemo.grpc.CountryServiceGrpc
import com.example.grpcservicedemo.server.CountryRepository
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.springframework.transaction.annotation.Transactional

@GRpcService
@Transactional
class CountryService(
        private val countryRepository: CountryRepository
) : CountryServiceGrpc.CountryServiceImplBase() {

    override fun getCountries(
            request: CountryOuterClass.CountryRequest?,
            responseObserver: StreamObserver<CountryOuterClass.CountriesResponse>?
    ) {
        val responseBuilder = CountryOuterClass.CountriesResponse.newBuilder()
        responseBuilder.addAllCountry(countryRepository.findAll().map {
            CountryOuterClass.Country
                    .newBuilder()
                    .setId(it.id!!)
                    .setIdentifier(it.identifier)
                    .setName(it.name)
                    .build()
        })

        println("Serving getCountries with: ${responseBuilder.build().countryList.map { it.name }}")

        responseObserver?.onNext(responseBuilder.build())
        responseObserver?.onCompleted()
    }
}
