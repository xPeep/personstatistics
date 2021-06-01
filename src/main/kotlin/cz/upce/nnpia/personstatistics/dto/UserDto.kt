package cz.upce.nnpia.personstatistics.dto

import cz.upce.nnpia.personstatistics.entity.ApplicationUser
import cz.upce.nnpia.personstatistics.entity.UserMeasurement
import cz.upce.nnpia.personstatistics.entity.UserPhoto
import cz.upce.nnpia.personstatistics.security.UserRole

data class UserDto(
	val id: Long?,
	val username: String,
	val password: String,
	val firstName: String,
	val lastName: String,
	val emailAddress: String,
	var role: UserRole?,
	val userPhoto: MutableList<UserPhoto>?,
	val userMeasurement: MutableList<UserMeasurement>?,
) {
	fun toEntityClass(): ApplicationUser {
		val user= ApplicationUser(
			this.username,
			this.password,
			this.role ?: UserRole.USER,
			this.firstName,
			this.lastName,
			this.emailAddress,
			this.userMeasurement ?: mutableListOf(),
			this.userPhoto ?: mutableListOf()
		)
		user.id = this.id
		return user
	}
}
