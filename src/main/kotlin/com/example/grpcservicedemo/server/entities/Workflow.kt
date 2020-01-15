package com.example.grpcservicedemo.server.entities

import javax.persistence.Entity

@Entity
class Workflow(
        var workflowId: Long,
        var folder: String,
        var name: String
) : AbstractJpaPersistable<Long>() {

}
