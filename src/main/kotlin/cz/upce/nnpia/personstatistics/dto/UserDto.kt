package cz.upce.nnpia.personstatistics.dto

import cz.upce.nnpia.personstatistics.entity.ApplicationUser
import cz.upce.nnpia.personstatistics.security.UserRole

class UserDto(
	var id: Long? = null,
	var username: String? = null,
	var password: String? = null,
	var role: UserRole? = null
) {
	fun toEntityClass(): ApplicationUser {
		val user = ApplicationUser(username, password, role)
		user.id = id
		return user
	}
}
