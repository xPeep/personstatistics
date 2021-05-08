package cz.upce.nnpia.personstatistics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
class PersonStatisticsApplication

fun main(args: Array<String>) {
	runApplication<PersonStatisticsApplication>(*args)
}
