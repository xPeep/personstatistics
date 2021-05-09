package cz.upce.nnpia.personstatistics.service

import cz.upce.nnpia.personstatistics.entity.PersonMeasurement
import cz.upce.nnpia.personstatistics.entity.PersonMeasurementType
import cz.upce.nnpia.personstatistics.repository.PersonMeasurementRepository
import cz.upce.nnpia.personstatistics.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalStateException
import java.time.LocalDateTime

@Service
class PersonMeasurementServiceImpl
@Autowired constructor(
	private val personService: PersonServiceImpl,
	private val personMeasurementRepository: PersonMeasurementRepository
) : PersonMeasurementService {

	override fun addMeasurement(personId: Long, value: Double, type: PersonMeasurementType) {
		val person = personService.getPerson(personId) ?: throw IllegalStateException("Person not found by id")
		person.personMeasurement.add(PersonMeasurement(LocalDateTime.now(), value, type))

	}

	override fun editMeasurement(personId: Long, personMeasurementId: Long, value: Double) {
		TODO("Not yet implemented")
	}

	override fun removeMeasurement(personId: Long, personMeasurementId: Long) {
		TODO("Not yet implemented")
	}
}
