package cz.upce.nnpia.personstatistics.dto

import cz.upce.nnpia.personstatistics.entity.UserMeasurement
import cz.upce.nnpia.personstatistics.entity.UserMeasurementType
import java.time.LocalDateTime

data class UserMeasurementDto(
	val id: Long? = null,
	val value: Double = 0.0,
	val timestamp: LocalDateTime = LocalDateTime.now(),
	val type: UserMeasurementType = UserMeasurementType.NONE,
	var userId: Long? = null
) {
	fun toEntityClass(): UserMeasurement {
		val userMeasurement = UserMeasurement(timestamp, value, type, null)
		userMeasurement.id = id
		return userMeasurement
	}
}
