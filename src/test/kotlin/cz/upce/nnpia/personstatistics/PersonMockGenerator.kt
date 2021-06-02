package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.dto.UserDto
import cz.upce.nnpia.personstatistics.dto.UserMeasurementDto
import cz.upce.nnpia.personstatistics.dto.UserPhotoDto
import cz.upce.nnpia.personstatistics.security.UserRole
import org.springframework.stereotype.Component
import java.lang.Math.random
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.ThreadLocalRandom
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

	fun createUserMedia(id: Long): UserPhotoDto {
		return UserPhotoDto(id, LocalDateTime.now(), generateName(5))
	}

	fun getRandomTime(): LocalDateTime {
		val minDay = LocalDate.of(2019, 1, 1).toEpochDay()
		val maxDay = LocalDate.of(2021, 5, 29).toEpochDay()
		val randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay)
		return LocalDate.ofEpochDay(randomDay).atStartOfDay()
	}

	fun createPersonalMeasurement(): UserMeasurementDto {
		return UserMeasurementDto(
			null,
			getRandomTime(),
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
