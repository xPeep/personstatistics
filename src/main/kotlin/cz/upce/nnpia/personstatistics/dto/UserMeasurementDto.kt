package cz.upce.nnpia.personstatistics.dto

import cz.upce.nnpia.personstatistics.entity.ApplicationUser
import cz.upce.nnpia.personstatistics.entity.UserMeasurement
import java.time.LocalDateTime

data class UserMeasurementDto(
	val id: Long? = null,
	val timestamp: LocalDateTime,
	val weight: Double?,
	val abdomenSize: Double?,
	val leftHandSize: Double?,
	val rightHandSize: Double?,
	val rightLeftSize: Double?,
	val rightRightSize: Double?,
	val chestSize: Double?,
	var userId: Long? = null
) {
	fun toEntityClass(applicationUser: ApplicationUser): UserMeasurement {
		val userMeasurement = UserMeasurement(
			this.timestamp,
			this.weight,
			this.abdomenSize,
			this.leftHandSize,
			this.rightHandSize,
			this.rightLeftSize,
			this.rightRightSize,
			this.chestSize,
			applicationUser
		)
		userMeasurement.id = this.id
		return userMeasurement
	}
}
