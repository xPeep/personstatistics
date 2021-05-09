package cz.upce.nnpia.personstatistics.service.interfaces

import cz.upce.nnpia.personstatistics.dto.PersonDto


interface PersonService {
	fun addPerson(personDto: PersonDto): PersonDto
	fun removePerson(personId: Long)
	fun getPerson(personId: Long): PersonDto?
	fun getAllPersons(): List<PersonDto>
}
