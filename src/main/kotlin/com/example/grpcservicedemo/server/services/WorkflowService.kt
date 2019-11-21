package com.example.grpcservicedemo.server.services

import com.example.grpcservicedemo.grpc.WorkflowOuterClass
import com.example.grpcservicedemo.grpc.WorkflowServiceGrpc
import com.example.grpcservicedemo.server.repositories.WorkflowRepository
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.springframework.transaction.annotation.Transactional

@GRpcService
@Transactional
class WorkflowService(
        private val workflowRepository: WorkflowRepository
) : WorkflowServiceGrpc.WorkflowServiceImplBase() {
    override fun getWorkflows(request: WorkflowOuterClass.WorkflowRequest?, responseObserver: StreamObserver<WorkflowOuterClass.WorkflowResponse>?) {
        val responseBuilder = WorkflowOuterClass.WorkflowResponse.newBuilder()
        responseBuilder.addAllWorkflow(workflowRepository.findAll().map { workflow ->
            WorkflowOuterClass.Workflow
                    .newBuilder()
                    .setViewId(workflow.viewId)
                    .setFolder(workflow.folder)
                    .setName(workflow.name)
                    .build()
        })

        println("Serving getWorkflows with: ${responseBuilder.build().workflowList.map { it.name }}")
        responseObserver?.onNext(responseBuilder.build())
        responseObserver?.onCompleted()
    }
}
