package com.example.grpcservicedemo.server.entities

import javax.persistence.Entity

@Entity
class Workflow(
        var viewId: Long,
        var folder: String,
        var name: String
) : AbstractJpaPersistable<Long>() {

}
