package com.example.grpcservicedemo.server.repositories

import com.example.grpcservicedemo.server.entities.Workflow
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkflowRepository : CrudRepository<Workflow, Long>
