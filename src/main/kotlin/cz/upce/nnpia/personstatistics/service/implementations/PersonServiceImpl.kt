package cz.upce.nnpia.personstatistics.service.implementations

import cz.upce.nnpia.personstatistics.dto.PersonDto
import cz.upce.nnpia.personstatistics.entity.Person
import cz.upce.nnpia.personstatistics.repository.PersonRepository
import cz.upce.nnpia.personstatistics.service.interfaces.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl
@Autowired constructor(
	private val personRepository: PersonRepository
) : PersonService {

	override fun addPerson(person: PersonDto) {
		TODO("Not yet implemented")
	}

	override fun removePerson(personId: Long) {
		TODO("Not yet implemented")
	}

	override fun getPerson(personId: Long): PersonDto? {
		TODO("Not yet implemented")
	}

	override fun getAllPersons(): List<PersonDto> {
		TODO("Not yet implemented")
	}

}
