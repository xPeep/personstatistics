package cz.upce.nnpia.personstatistics.service.implementations

import cz.upce.nnpia.personstatistics.dto.PersonMeasurementDto
import cz.upce.nnpia.personstatistics.dto.PersonMeasurementIntervalDto
import cz.upce.nnpia.personstatistics.repository.PersonRepository
import cz.upce.nnpia.personstatistics.service.interfaces.PersonMeasurementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PersonMeasurementServiceImpl
@Autowired constructor(
	private val personRepository: PersonRepository
) : PersonMeasurementService {

	override fun addMeasurement(personMeasurementDto: PersonMeasurementDto) {
		val person =
			personRepository.findByIdOrNull(personMeasurementDto.personId) ?: throw IllegalStateException("Person was not found by id")
		val personMeasurementDtoLocal = personMeasurementDto.toEntityClass()
		person.personMeasurement.add(personMeasurementDtoLocal)
		personMeasurementDtoLocal.person = person
		personRepository.save(person)
	}

	override fun removeMeasurement(personId: Long, personMeasurementId: Long) {
		val person =
			personRepository.findByIdOrNull(personId) ?: throw IllegalStateException("Person was not found by id")
		person.personMeasurement.removeIf { it.id == personMeasurementId }
		personRepository.save(person)
	}

	override fun getAllMeasurements(personId: Long): List<PersonMeasurementDto> {
		val person =
			personRepository.findByIdOrNull(personId) ?: throw IllegalStateException("Person was not found by id")
		return person.personMeasurement.map { it.toDtoClass() }
	}

	override fun getMeasurementsByInterval(personMeasurementIntervalDto: PersonMeasurementIntervalDto): List<PersonMeasurementDto> {
		val personMeasurement = (personRepository.findByIdOrNull(personMeasurementIntervalDto.personId)
			?: throw IllegalStateException("Person was not found by id")).personMeasurement
		return personMeasurement
			.filter { personMeasurementIntervalDto.typeList.contains(it.type) }
			.filter {
				it.timeStamp?.isAfter(personMeasurementIntervalDto.start) == true && it.timeStamp?.isBefore(
					personMeasurementIntervalDto.end
				) == true
			}
			.map { it.toDtoClass() }
	}

}
