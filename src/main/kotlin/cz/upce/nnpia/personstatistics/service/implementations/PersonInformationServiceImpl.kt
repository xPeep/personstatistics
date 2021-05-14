package cz.upce.nnpia.personstatistics.service.implementations

import cz.upce.nnpia.personstatistics.dto.PersonInformationDto
import cz.upce.nnpia.personstatistics.repository.PersonInformationRepository
import cz.upce.nnpia.personstatistics.repository.PersonRepository
import cz.upce.nnpia.personstatistics.service.interfaces.PersonInformationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PersonInformationServiceImpl
@Autowired constructor(
	private val personRepository: PersonRepository,
	private val personInformationRepository: PersonInformationRepository
) : PersonInformationService {

	@Transactional
	override fun getPersonInformation(personId: Long): PersonInformationDto? {
		return (personInformationRepository.findByIdOrNull(personId)
			?: throw IllegalStateException("Person was not found by id")).toDtoClass()
	}

	@Transactional
	override fun addPersonInformation(personInformationDto: PersonInformationDto) {
		val personInformation = personInformationDto.toEntityClass()
		personInformation.person = personRepository.findByIdOrNull(personInformationDto.id ?: -1)
		personInformationRepository.save(personInformation)
	}

	@Transactional
	override fun removePersonInformation(personId: Long) {
		personInformationRepository.deleteById(personId)
	}
}
