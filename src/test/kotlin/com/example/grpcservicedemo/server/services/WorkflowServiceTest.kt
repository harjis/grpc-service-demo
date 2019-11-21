package com.example.grpcservicedemo.server.services

import com.example.grpcservicedemo.grpc.WorkflowOuterClass
import com.example.grpcservicedemo.grpc.WorkflowServiceGrpc
import io.grpc.ManagedChannelBuilder
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WorkflowServiceTest {
    @Autowired
    lateinit var gRpcServerProperties: GRpcServerProperties

    @Test
    fun getsWorkflows() {
        val channel = ManagedChannelBuilder
                .forAddress("localhost", gRpcServerProperties.runningPort)
                .usePlaintext()
                .build()
        val stub = WorkflowServiceGrpc.newBlockingStub(channel)
        val request = WorkflowOuterClass.WorkflowRequest.newBuilder().build()
        val response = stub.getWorkflows(request).workflowList

        Assertions.assertThat(response.size).isEqualTo(5)
    }

    @Test
    fun getsWorkflowsAsync() {
        val channel = ManagedChannelBuilder
                .forAddress("localhost", gRpcServerProperties.runningPort)
                .usePlaintext()
                .build()
        val stub = WorkflowServiceGrpc.newFutureStub(channel)
        val request = WorkflowOuterClass.WorkflowRequest.newBuilder().build()
        val response = stub.getWorkflows(request).get().workflowList

        Assertions.assertThat(response.size).isEqualTo(5)
    }
}
