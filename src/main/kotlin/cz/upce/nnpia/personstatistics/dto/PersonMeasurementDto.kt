package cz.upce.nnpia.personstatistics.dto

import cz.upce.nnpia.personstatistics.entity.PersonMeasurement
import cz.upce.nnpia.personstatistics.entity.PersonMeasurementType
import java.time.LocalDateTime

data class PersonMeasurementDto(
	val id: Long? = null,
	val value: Double = 0.0,
	val timestamp: LocalDateTime = LocalDateTime.now(),
	val type: PersonMeasurementType = PersonMeasurementType.NONE,
	var personId: Long? = null
) {
	fun toEntityClass(): PersonMeasurement {
		val personMeasurement = PersonMeasurement(timestamp, value, type, null)
		personMeasurement.id = id
		return personMeasurement
	}
}
