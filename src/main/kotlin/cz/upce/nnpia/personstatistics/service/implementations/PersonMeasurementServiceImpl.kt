package cz.upce.nnpia.personstatistics.service.implementations

import cz.upce.nnpia.personstatistics.dto.PersonMeasurementDto
import cz.upce.nnpia.personstatistics.entity.PersonMeasurement
import cz.upce.nnpia.personstatistics.entity.PersonMeasurementType
import cz.upce.nnpia.personstatistics.repository.PersonMeasurementRepository
import cz.upce.nnpia.personstatistics.service.interfaces.PersonMeasurementService
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

	override fun addMeasurement(personMeasurementDto: PersonMeasurementDto) {
		TODO("Not yet implemented")
	}

	override fun editMeasurement(personId: Long, personMeasurementDto: PersonMeasurementDto) {
		TODO("Not yet implemented")
	}

	override fun removeMeasurement(personId: Long, personMeasurementId: Long) {
		TODO("Not yet implemented")
	}

	override fun getAllMeasurements(personId: Long): List<PersonMeasurementDto> {
		TODO("Not yet implemented")
	}

	override fun getMeasurementsByInterval(
		personId: Long,
		start: LocalDateTime,
		end: LocalDateTime,
		typeList: List<PersonMeasurementType>
	): List<PersonMeasurementDto> {
		TODO("Not yet implemented")
	}

}
