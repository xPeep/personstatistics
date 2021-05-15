package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.dto.UserDto
import cz.upce.nnpia.personstatistics.dto.UserInformationDto
import cz.upce.nnpia.personstatistics.dto.UserMeasurementDto
import cz.upce.nnpia.personstatistics.entity.UserMeasurementType
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

	fun createPersonal(): UserDto {
		return UserDto(
			null,
			generateName(5),
			generateName(5),
		)
	}

	fun createPersonalInformation(id: Long): UserInformationDto {
		return UserInformationDto(id, generateName(4), generateName(5), generateName(8), mutableListOf())
	}

	fun createPersonalMeasurement(): UserMeasurementDto {
		return UserMeasurementDto(
			null,
			random().roundToInt().toDouble(),
			LocalDateTime.now(),
			UserMeasurementType.CHEST
		)
	}

	fun createPersonalMeasurement(dateString: String): UserMeasurementDto {
		return UserMeasurementDto(
			null,
			random().roundToInt().toDouble(),
			createLocalDateTimeFromString(dateString),
			UserMeasurementType.CHEST
		)
	}

	fun createLocalDateTimeFromString(dateString: String): LocalDateTime {
		return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm"))
	}

}
