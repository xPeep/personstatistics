package cz.upce.nnpia.personstatistics.service.implementations

import cz.upce.nnpia.personstatistics.dto.PersonDto
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

	override fun addPerson(personDto: PersonDto) {
		personRepository.save(personDto.toEntityClass())
	}

	override fun removePerson(personId: Long) {
		personRepository.deleteById(personId)
	}

	override fun getPerson(personId: Long): PersonDto? {
		return (personRepository.findByIdOrNull(personId) ?: return null).toDtoClass()
	}

	override fun getAllPersons(): List<PersonDto> {
		return personRepository.findAll().map { it.toDtoClass() }
	}
}
