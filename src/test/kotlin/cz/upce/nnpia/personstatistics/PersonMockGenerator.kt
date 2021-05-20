package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.dto.UserDto
import cz.upce.nnpia.personstatistics.dto.UserMeasurementDto
import cz.upce.nnpia.personstatistics.dto.UserMediaDto
import cz.upce.nnpia.personstatistics.security.UserRole
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
			generateName(5),
			generateName(5),
			generateName(5),
			UserRole.USER,
			mutableListOf(),
			mutableListOf()
		)
	}

	fun createUserMedia(id: Long): UserMediaDto {
		return UserMediaDto(id, null)
	}

	fun createPersonalMeasurement(): UserMeasurementDto {
		return UserMeasurementDto(
			null,
			LocalDateTime.now(),
			random().roundToInt().toDouble(),
			random().roundToInt().toDouble(),
			random().roundToInt().toDouble(),
			random().roundToInt().toDouble(),
			random().roundToInt().toDouble(),
			random().roundToInt().toDouble(),
			random().roundToInt().toDouble()
		)
	}

	fun createPersonalMeasurement(dateString: String): UserMeasurementDto {
		return UserMeasurementDto(
			null,
			createLocalDateTimeFromString(dateString),
			random().roundToInt().toDouble(),
			random().roundToInt().toDouble(),
			random().roundToInt().toDouble(),
			random().roundToInt().toDouble(),
			random().roundToInt().toDouble(),
			random().roundToInt().toDouble(),
			random().roundToInt().toDouble()
		)
	}

	fun createLocalDateTimeFromString(dateString: String): LocalDateTime {
		return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm"))
	}

}
