package cz.upce.nnpia.personstatistics.dto

import cz.upce.nnpia.personstatistics.entity.PersonMeasurement
import cz.upce.nnpia.personstatistics.entity.PersonMeasurementType
import java.time.LocalDateTime

data class PersonMeasurementDto(
	val id: Long,
	val value: Double,
	val timestamp: LocalDateTime,
	val type: PersonMeasurementType
) {
	fun toEntityClass(): PersonMeasurement {
		val personMeasurement = PersonMeasurement(timestamp, value, type, null)
		personMeasurement.id = id
		return personMeasurement
	}
}
