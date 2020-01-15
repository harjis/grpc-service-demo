package com.example.grpcservicedemo.server.services

import com.example.grpcservicedemo.server.repositories.WorkflowRepository
import fi.relex.processor2.fastorm.cluster.rpc.workflow.stubs.WorkflowOuterClass
import fi.relex.processor2.fastorm.cluster.rpc.workflow.stubs.WorkflowServiceGrpc
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class WorkflowService(
        private val workflowRepository: WorkflowRepository
) : WorkflowServiceGrpc.WorkflowServiceImplBase() {
    override fun getWorkflows(request: WorkflowOuterClass.WorkflowsRequest?, responseObserver: StreamObserver<WorkflowOuterClass.WorkflowsResponse>) {
        val responseBuilder = WorkflowOuterClass.WorkflowsResponse.newBuilder()
        responseBuilder.addAllWorkflows(workflowRepository.findAll().map { workflow ->
            // TODO I don't get why I need .let here
            workflow!!.id?.let {
                WorkflowOuterClass.Workflow
                        .newBuilder()
                        .setWorkflowId(workflow.workflowId)
                        .setFolder(workflow.folder)
                        .setName(workflow.name)
                        .build()
            }
        })

        println("Serving getWorkflows with: ${responseBuilder.build().workflowsList.map { it.name }}")
        responseObserver.onNext(responseBuilder.build())
        responseObserver.onCompleted()
    }

    override fun getWorkflow(request: WorkflowOuterClass.WorkflowRequest?, responseObserver: StreamObserver<WorkflowOuterClass.WorkflowResponse>) {
        val responseBuilder = WorkflowOuterClass.WorkflowResponse.newBuilder()
        // Why you .let here?!
        val workflow = request?.id?.let { workflowRepository.findById(it).orElseThrow { Exception("Not found") } }
        responseBuilder.workflow = workflow!!.id?.let {
            WorkflowOuterClass.Workflow
                    .newBuilder()
                    .setWorkflowId(workflow.workflowId)
                    .setFolder(workflow.folder)
                    .setName(workflow.name)
                    .build()
        }

        responseObserver.onNext(responseBuilder.build())
        responseObserver.onCompleted()
    }
}
