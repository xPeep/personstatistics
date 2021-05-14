package cz.upce.nnpia.personstatistics.service.implementations

import cz.upce.nnpia.personstatistics.dto.PersonDto
import cz.upce.nnpia.personstatistics.repository.PersonRepository
import cz.upce.nnpia.personstatistics.service.interfaces.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PersonServiceImpl
@Autowired constructor(
	private val personRepository: PersonRepository
) : PersonService {

	@Transactional
	override fun addPerson(personDto: PersonDto): PersonDto {
		val person = personDto.toEntityClass()
		personRepository.save(person)
		return person.toDtoClass()
	}

	@Transactional
	override fun removePerson(personId: Long) {
		personRepository.deleteById(personId)
	}

	@Transactional
	override fun getPerson(personId: Long): PersonDto? {
		return (personRepository.findByIdOrNull(personId) ?: return null).toDtoClass()
	}

	@Transactional
	override fun getAllPersons(): List<PersonDto> {
		return personRepository.findAll().map { it.toDtoClass() }
	}
}
