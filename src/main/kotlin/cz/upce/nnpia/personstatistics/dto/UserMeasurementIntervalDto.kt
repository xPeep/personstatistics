package cz.upce.nnpia.personstatistics.dto

import cz.upce.nnpia.personstatistics.entity.UserMeasurementType
import java.time.LocalDateTime

data class UserMeasurementIntervalDto(
	var userId: Long,
	var start: LocalDateTime,
	var end: LocalDateTime,
	var typeList: List<UserMeasurementType>
)
