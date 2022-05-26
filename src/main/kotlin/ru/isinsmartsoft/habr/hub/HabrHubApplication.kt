package ru.isinsmartsoft.habr.hub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties
class HabrHubApplication

fun main(args: Array<String>) {
    runApplication<HabrHubApplication>(*args)
}
