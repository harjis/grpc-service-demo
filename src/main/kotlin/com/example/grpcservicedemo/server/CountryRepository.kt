package com.example.grpcservicedemo.server

import com.example.grpcservicedemo.server.entities.Country
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CountryRepository : CrudRepository<Country, Long>
