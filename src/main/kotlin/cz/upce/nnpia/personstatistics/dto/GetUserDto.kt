package cz.upce.nnpia.personstatistics.dto

import cz.upce.nnpia.personstatistics.entity.ApplicationUser
import cz.upce.nnpia.personstatistics.entity.UserMeasurement
import cz.upce.nnpia.personstatistics.entity.UserPhoto
import cz.upce.nnpia.personstatistics.security.UserRole

data class GetUserDto(
	val id: Long?,
	val username: String,
	val firstName: String,
	val lastName: String,
	val emailAddress: String
)
