package cz.upce.nnpia.personstatistics.dto

import cz.upce.nnpia.personstatistics.entity.Person

data class PersonDto(
	val id: Long? = null,
	val username: String = "",
	val password: String = ""
) {
	fun toEntityClass(): Person {
		val person = Person(username, password, mutableListOf())
		person.id = id
		return person
	}
}
