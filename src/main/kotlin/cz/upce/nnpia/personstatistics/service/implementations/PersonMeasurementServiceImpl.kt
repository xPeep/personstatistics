package cz.upce.nnpia.personstatistics.service.implementations

import cz.upce.nnpia.personstatistics.dto.PersonMeasurementDto
import cz.upce.nnpia.personstatistics.entity.PersonMeasurementType
import cz.upce.nnpia.personstatistics.repository.PersonMeasurementRepository
import cz.upce.nnpia.personstatistics.repository.PersonRepository
import cz.upce.nnpia.personstatistics.service.interfaces.PersonMeasurementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PersonMeasurementServiceImpl
@Autowired constructor(
	private val personRepository: PersonRepository,
	private val personMeasurementRepository: PersonMeasurementRepository
) : PersonMeasurementService {


	override fun addMeasurement(personId: Long, personMeasurementDto: PersonMeasurementDto) {
		val person =
			personRepository.findByIdOrNull(personId) ?: throw IllegalStateException("Person was not found by id")
		person.personMeasurement.add(personMeasurementDto.toEntityClass())
		personRepository.save(person)
	}

	override fun removeMeasurement(personMeasurementId: Long) {
		personMeasurementRepository.deleteById(personMeasurementId)
	}

	override fun getAllMeasurements(personId: Long): List<PersonMeasurementDto> {
		val person =
			personRepository.findByIdOrNull(personId) ?: throw IllegalStateException("Person was not found by id")
		return person.personMeasurement.map { it.toDtoClass() }
	}

	override fun getMeasurementsByInterval(
		personId: Long,
		start: LocalDateTime,
		end: LocalDateTime,
		typeList: List<PersonMeasurementType>
	): List<PersonMeasurementDto> {
		val personMeasurement = (personRepository.findByIdOrNull(personId)
			?: throw IllegalStateException("Person was not found by id")).personMeasurement
		return personMeasurement
			.filter { it.timeStamp?.isAfter(start) == true && it.timeStamp?.isBefore(end) == true }
			.map { it.toDtoClass() }
	}

}
