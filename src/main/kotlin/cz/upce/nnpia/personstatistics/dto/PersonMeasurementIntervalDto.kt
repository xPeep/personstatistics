package cz.upce.nnpia.personstatistics.dto

import cz.upce.nnpia.personstatistics.entity.PersonMeasurementType
import java.time.LocalDateTime

data class PersonMeasurementIntervalDto(
	var personId: Long,
	var start: LocalDateTime,
	var end: LocalDateTime,
	var typeList: List<PersonMeasurementType>
)
