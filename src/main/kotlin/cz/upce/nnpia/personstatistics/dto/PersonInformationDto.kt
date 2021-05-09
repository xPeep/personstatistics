package cz.upce.nnpia.personstatistics.dto


import cz.upce.nnpia.personstatistics.entity.PersonInformation

data class PersonInformationDto(
	val id: Long? = null,
	val firstName: String = "",
	val lastName: String = "",
	val emailAddress: String = "",
) {
	fun toEntityClass(): PersonInformation {
		val personInformation = PersonInformation(firstName, lastName, emailAddress, null)
		personInformation.id = id
		return personInformation
	}
}


