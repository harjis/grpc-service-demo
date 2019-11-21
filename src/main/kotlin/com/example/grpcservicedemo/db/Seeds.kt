package com.example.grpcservicedemo.db

import com.example.grpcservicedemo.server.repositories.CountryRepository
import com.example.grpcservicedemo.server.entities.Country
import com.example.grpcservicedemo.server.entities.Workflow
import com.example.grpcservicedemo.server.repositories.WorkflowRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.Transactional

@Configuration
class Seeds {
    @Bean
    @Transactional
    fun initDB(
            countryRepository: CountryRepository,
            workflowRepository: WorkflowRepository
    ) = CommandLineRunner {
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

        val workflow1 = Workflow(viewId = 1, folder = "Folder 1", name = "Workflow 1")
        val workflow2 = Workflow(viewId = 2, folder = "Folder 1", name = "Workflow 2")
        val workflow3 = Workflow(viewId = 3, folder = "Folder 1", name = "Workflow 3")
        val workflow4 = Workflow(viewId = 4, folder = "Folder 2", name = "Workflow 4")
        val workflow5 = Workflow(viewId = 5, folder = "Folder 2", name = "Workflow 5")
        workflowRepository.save(workflow1)
        workflowRepository.save(workflow2)
        workflowRepository.save(workflow3)
        workflowRepository.save(workflow4)
        workflowRepository.save(workflow5)
    }
}
