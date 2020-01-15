package com.example.grpcservicedemo.db

import com.example.grpcservicedemo.server.entities.Country
import com.example.grpcservicedemo.server.entities.View
import com.example.grpcservicedemo.server.entities.Workflow
import com.example.grpcservicedemo.server.repositories.CountryRepository
import com.example.grpcservicedemo.server.repositories.ViewRepository
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
            workflowRepository: WorkflowRepository,
            viewRepository: ViewRepository
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

        val workflow1 = Workflow(workflowId = 1, folder = "Folder", name = "Campaigns")
        val workflow2 = Workflow(workflowId = 2, folder = "Folder", name = "Chain")
        val workflow3 = Workflow(workflowId = 3, folder = "Folder", name = "Something else")
        val workflow4 = Workflow(workflowId = 4, folder = "Folder", name = "Workflow 4")
        val workflow5 = Workflow(workflowId = 5, folder = "Folder", name = "Workflow 5")
        workflowRepository.save(workflow1)
        workflowRepository.save(workflow2)
        workflowRepository.save(workflow3)
        workflowRepository.save(workflow4)
        workflowRepository.save(workflow5)

        val view1 = View(viewId = 1, folder = "Folder", name = "Test 1")
        val view2 = View(viewId = 2, folder = "Folder", name = "Test 2")
        viewRepository.save(view1)
        viewRepository.save(view2)
    }
}
