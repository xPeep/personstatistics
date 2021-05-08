package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.entity.Person
import cz.upce.nnpia.personstatistics.entity.PersonInformation
import cz.upce.nnpia.personstatistics.entity.PersonMeasurement
import cz.upce.nnpia.personstatistics.entity.PersonMeasurementType
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PersonMockGenerator {

	private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

	private fun generateName(size: Int): String {
		return (1..size)
			.map { kotlin.random.Random.nextInt(0, charPool.size) }
			.map(charPool::get)
			.joinToString("")
	}

	fun createPersonal(): Person {
		return Person(
			generateName(4),
			generateName(5),
			createPersonalInformation(),
			mutableListOf(
				createPersonalMeasurement(),
				createPersonalMeasurement(),
				createPersonalMeasurement()
			)
		)
	}

	fun createPersonalInformation(): PersonInformation {
		return PersonInformation(generateName(4), generateName(5), generateName(8))
	}

	fun createPersonalMeasurement(): PersonMeasurement {
		return PersonMeasurement(LocalDateTime.now(), 15.7, PersonMeasurementType.CHEST)
	}


}
