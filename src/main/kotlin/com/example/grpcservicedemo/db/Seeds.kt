package com.example.grpcservicedemo.db

import com.example.grpcservicedemo.server.CountryRepository
import com.example.grpcservicedemo.server.entities.Country
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.Transactional

@Configuration
class Seeds {
    @Bean
    @Transactional
    fun initDB(countryRepository: CountryRepository) = CommandLineRunner {
        println("Init DB")
        if (countryRepository.count() > 0) {
            return@CommandLineRunner
        }

        val country1 = Country(identifier = "A", name = "Austria")
        val country2 = Country(identifier = "B", name = "Brasil")
        val country3 = Country(identifier = "C", name = "Chiile")
        val country4 = Country(identifier = "D", name = "Danmakr")
        val country5 = Country(identifier = "E", name = "Eesti")
        countryRepository.save(country1)
        countryRepository.save(country2)
        countryRepository.save(country3)
        countryRepository.save(country4)
        countryRepository.save(country5)
    }
}
