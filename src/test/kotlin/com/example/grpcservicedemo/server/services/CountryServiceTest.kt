package com.example.grpcservicedemo.server.services

import com.example.grpcservicedemo.grpc.Country
import com.example.grpcservicedemo.grpc.CountryServiceGrpc
import io.grpc.ManagedChannelBuilder
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CountryServiceTest {
    @Autowired
    lateinit var gRpcServerProperties: GRpcServerProperties

    @Test
    fun getsCountries(){
        val channel = ManagedChannelBuilder
                .forAddress("localhost", gRpcServerProperties.runningPort)
                .usePlaintext()
                .build()
        val countryStub = CountryServiceGrpc.newBlockingStub(channel)
        val request = Country.CountryRequest
                .newBuilder()
                .addId("A")
                .build()
        val response = countryStub.getCountries(request).countryList
        Assertions.assertThat(response.size).isEqualTo(1)
    }
}
