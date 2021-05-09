package cz.upce.nnpia.personstatistics.dto

import cz.upce.nnpia.personstatistics.entity.PersonMeasurementType
import java.time.LocalDateTime

data class PersonMeasurementDto(
	val id: Long,
	val value: Double,
	val timestamp: LocalDateTime,
	val type: PersonMeasurementType
)
