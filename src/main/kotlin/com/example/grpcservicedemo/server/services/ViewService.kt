package com.example.grpcservicedemo.server.services

import com.example.grpcservicedemo.server.repositories.ViewRepository
import fi.relex.processor2.fastorm.cluster.rpc.view.stubs.ViewOuterClass
import fi.relex.processor2.fastorm.cluster.rpc.view.stubs.ViewServiceGrpc
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class ViewService (private val viewRepository: ViewRepository) : ViewServiceGrpc.ViewServiceImplBase() {
    override fun getViews(request: ViewOuterClass.ViewsRequest?, responseObserver: StreamObserver<ViewOuterClass.ViewsResponse>) {
        val responseBuilder = ViewOuterClass.ViewsResponse.newBuilder()
        responseBuilder.addAllViews(viewRepository.findAll().map { view ->
            ViewOuterClass.View
                    .newBuilder()
                    .setViewId(view.viewId)
                    .setFolder(view.folder)
                    .setName(view.name)
                    .build()
        })

        println("Serving getViews with: ${responseBuilder.build().viewsList.map { it.name }}")
        responseObserver.onNext(responseBuilder.build())
        responseObserver.onCompleted()
    }
}
