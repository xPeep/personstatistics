package cz.upce.nnpia.personstatistics.service

import cz.upce.nnpia.personstatistics.entity.Person
import cz.upce.nnpia.personstatistics.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl
@Autowired constructor(
	private val personRepository: PersonRepository
) : PersonService {

	override fun addPerson(person: Person) {
		personRepository.save(person)
	}

	override fun removePerson(personId: Long) {
		personRepository.deleteById(personId)
	}

	override fun getPerson(personId: Long): Person? {
		return personRepository.findByIdOrNull(personId)
	}

	override fun getAllPersons(): List<Person> {
		return personRepository.findAll()
	}
}
