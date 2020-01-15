package com.example.grpcservicedemo.server.services

import com.example.grpcservicedemo.grpc.WorkflowOuterClass
import com.example.grpcservicedemo.grpc.WorkflowServiceGrpc
import com.example.grpcservicedemo.server.repositories.WorkflowRepository
import io.grpc.ManagedChannelBuilder
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WorkflowServiceTest {
    @Autowired
    lateinit var workflowRepository: WorkflowRepository
    @Autowired
    lateinit var gRpcServerProperties: GRpcServerProperties

    @Test
    fun getsWorkflows() {
        val channel = ManagedChannelBuilder
                .forAddress("localhost", gRpcServerProperties.runningPort)
                .usePlaintext()
                .build()
        val stub = WorkflowServiceGrpc.newBlockingStub(channel)
        val request = WorkflowOuterClass.WorkflowsRequest.newBuilder().build()
        val response = stub.getWorkflows(request).workflowsList

        Assertions.assertThat(response.size).isEqualTo(5)
    }

    @Test
    fun getsWorkflowsAsync() {
        val channel = ManagedChannelBuilder
                .forAddress("localhost", gRpcServerProperties.runningPort)
                .usePlaintext()
                .build()
        val stub = WorkflowServiceGrpc.newFutureStub(channel)
        val request = WorkflowOuterClass.WorkflowsRequest.newBuilder().build()
        val response = stub.getWorkflows(request).get().workflowsList
        val firstWorkflow = response.first()
        val firstWorkflowDB = workflowRepository.findAll().first()

        Assertions.assertThat(response.size).isEqualTo(5)
        Assertions.assertThat(firstWorkflow.workflowId).isEqualTo(firstWorkflowDB.workflowId)
        Assertions.assertThat(firstWorkflow.folder).isEqualTo(firstWorkflowDB.folder)
        Assertions.assertThat(firstWorkflow.name).isEqualTo(firstWorkflowDB.name)
    }

    @Test
    fun getsWorkFlow() {
        val channel = ManagedChannelBuilder
                .forAddress("localhost", gRpcServerProperties.runningPort)
                .usePlaintext()
                .build()
        val stub = WorkflowServiceGrpc.newBlockingStub(channel)
        val firstWorkflowDB = workflowRepository.findAll().first()
        val request = firstWorkflowDB.id?.let {
            WorkflowOuterClass.WorkflowRequest
                    .newBuilder()
                    .setId(it)
                    .build()
        }
        val response = stub.getWorkflow(request).workflow
        Assertions.assertThat(response.workflowId).isEqualTo(firstWorkflowDB.workflowId)
        Assertions.assertThat(response.folder).isEqualTo(firstWorkflowDB.folder)
        Assertions.assertThat(response.name).isEqualTo(firstWorkflowDB.name)

    }
}
