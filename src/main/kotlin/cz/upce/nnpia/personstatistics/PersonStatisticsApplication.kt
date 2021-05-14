package cz.upce.nnpia.personstatistics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication


@SpringBootApplication
@ConfigurationPropertiesScan
class PersonStatisticsApplication

fun main(args: Array<String>) {
	runApplication<PersonStatisticsApplication>(*args)
}
