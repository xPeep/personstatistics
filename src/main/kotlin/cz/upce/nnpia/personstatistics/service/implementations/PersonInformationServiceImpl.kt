package cz.upce.nnpia.personstatistics.service.implementations

import cz.upce.nnpia.personstatistics.dto.PersonInformationDto
import cz.upce.nnpia.personstatistics.repository.PersonInformationRepository
import cz.upce.nnpia.personstatistics.repository.PersonRepository
import cz.upce.nnpia.personstatistics.service.interfaces.PersonInformationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PersonInformationServiceImpl
@Autowired constructor(
	private val personRepository: PersonRepository,
	private val personInformationRepository: PersonInformationRepository
) : PersonInformationService {

	override fun getPersonInformation(personId: Long): PersonInformationDto? {
		return (personInformationRepository.findByIdOrNull(personId)
			?: throw IllegalStateException("Person was not found by id")).toDtoClass()
	}

	override fun addPersonInformation(personId: Long, personInformationDto: PersonInformationDto) {
		val personInformation = personInformationDto.toEntityClass()
		personInformation.person =
			personRepository.findByIdOrNull(personId) ?: throw IllegalStateException("Person was not found by id")
		personInformationRepository.save(personInformation)
	}

	override fun removePersonInformation(personId: Long) {
		personInformationRepository.deleteById(personId)
	}
}
