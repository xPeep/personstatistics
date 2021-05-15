package cz.upce.nnpia.personstatistics.dto


import cz.upce.nnpia.personstatistics.entity.UserInformation
import cz.upce.nnpia.personstatistics.entity.UserMeasurement

data class UserInformationDto(
	val id: Long? = null,
	val firstName: String? = null,
	val lastName: String? = null,
	val emailAddress: String? = null,
	var userMeasurement: MutableList<UserMeasurement>
) {
	fun toEntityClass(): UserInformation {
		val userInformation = UserInformation(firstName, lastName, emailAddress, userMeasurement)
		userInformation.id = id
		return userInformation
	}
}


