package cz.upce.nnpia.personstatistics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication


@SpringBootApplication
@ConfigurationPropertiesScan
class UserStatisticsApplication

fun main(args: Array<String>) {
	runApplication<UserStatisticsApplication>(*args)
}
