package cz.upce.nnpia.personstatistics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PersonStatisticsApplication

fun main(args: Array<String>) {
	runApplication<PersonStatisticsApplication>(*args)
}
