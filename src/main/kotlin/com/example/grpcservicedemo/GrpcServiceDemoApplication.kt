package com.example.grpcservicedemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GrpcServiceDemoApplication

fun main(args: Array<String>) {
	runApplication<GrpcServiceDemoApplication>(*args)
}
