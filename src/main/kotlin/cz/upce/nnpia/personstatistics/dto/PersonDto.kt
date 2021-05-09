package cz.upce.nnpia.personstatistics.dto

data class PersonDto(
	val id: Long,
	val username: String,
	val password: String,
	val firstName: String,
	val lastName: String,
	val emailAddress: String
)
