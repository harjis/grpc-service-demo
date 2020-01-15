package com.example.grpcservicedemo.server.entities

import javax.persistence.Entity

@Entity
class View(
        var viewId: Long,
        var folder: String,
        var name: String
) : AbstractJpaPersistable<Long>() {

}
