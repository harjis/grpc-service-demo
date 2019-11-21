package com.example.grpcservicedemo.server.controllers

import com.example.grpcservicedemo.server.repositories.CountryRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/countries")
@Transactional
class CountryController(private val countryRepository: CountryRepository) {
    @GetMapping
    fun index() = countryRepository.findAll()
}
