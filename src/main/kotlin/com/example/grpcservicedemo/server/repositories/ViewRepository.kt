package com.example.grpcservicedemo.server.repositories

import com.example.grpcservicedemo.server.entities.View
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ViewRepository : CrudRepository<View, Long>
