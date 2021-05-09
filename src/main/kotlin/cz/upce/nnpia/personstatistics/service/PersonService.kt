package cz.upce.nnpia.personstatistics.service

import cz.upce.nnpia.personstatistics.entity.Person

interface PersonService {
	fun addPerson(person: Person)
	fun removePerson(personId: Long)
	fun getPerson(personId: Long): Person?
	fun getAllPersons(): List<Person>
}
