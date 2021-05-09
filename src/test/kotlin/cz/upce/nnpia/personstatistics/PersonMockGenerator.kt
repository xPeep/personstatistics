package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.dto.PersonDto
import cz.upce.nnpia.personstatistics.dto.PersonInformationDto
import cz.upce.nnpia.personstatistics.dto.PersonMeasurementDto
import cz.upce.nnpia.personstatistics.entity.PersonMeasurementType
import org.springframework.stereotype.Component
import java.lang.Math.random
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt


@Component
class PersonMockGenerator {

	private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

	private fun generateName(size: Int): String {
		return (1..size)
			.map { kotlin.random.Random.nextInt(0, charPool.size) }
			.map(charPool::get)
			.joinToString("")
	}

	fun createPersonal(): PersonDto {
		return PersonDto(
			null,
			generateName(5),
			generateName(5),
		)
	}

	fun createPersonalInformation(): PersonInformationDto {
		return PersonInformationDto(null, generateName(4), generateName(5), generateName(8))
	}

	fun createPersonalMeasurement(): PersonMeasurementDto {
		return PersonMeasurementDto(
			null,
			random().roundToInt().toDouble(),
			LocalDateTime.now(),
			PersonMeasurementType.CHEST
		)
	}

	fun createPersonalMeasurement(dateString: String): PersonMeasurementDto {
		return PersonMeasurementDto(
			null,
			random().roundToInt().toDouble(),
			createLocalDateTimeFromString(dateString),
			PersonMeasurementType.CHEST
		)
	}

	fun createLocalDateTimeFromString(dateString: String): LocalDateTime {
		return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm"))
	}

}
