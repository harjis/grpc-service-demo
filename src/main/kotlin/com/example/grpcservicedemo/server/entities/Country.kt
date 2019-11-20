package com.example.grpcservicedemo.server.entities

import javax.persistence.Entity

@Entity
class Country(
        var identifier: String,
        var name: String
) : AbstractJpaPersistable<Long>() {

}
