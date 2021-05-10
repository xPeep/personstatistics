package cz.upce.nnpia.personstatistics.dto

import cz.upce.nnpia.personstatistics.entity.Person

data class PersonDto(
	var id: Long? = null,
	var username: String? = null,
	var password: String? = null
) {
	fun toEntityClass(): Person {
		val person = Person(username, password, mutableListOf())
		person.id = id
		return person
	}
}
