package cz.upce.nnpia.personstatistics.dto

import java.time.LocalDateTime

data class UserMeasurementIntervalDto(
	var userId: Long? = null,
	val start: LocalDateTime? = null,
	val end: LocalDateTime? = null
)
