package cz.upce.nnpia.personstatistics.service.interfaces

import cz.upce.nnpia.personstatistics.dto.PersonMeasurementDto
import cz.upce.nnpia.personstatistics.entity.PersonMeasurementType
import java.time.LocalDateTime

interface PersonMeasurementService {
	fun addMeasurement(personId: Long, personMeasurementDto: PersonMeasurementDto)
	fun removeMeasurement(personMeasurementId: Long)
	fun getAllMeasurements(personId: Long): List<PersonMeasurementDto>
	fun getMeasurementsByInterval(
		personId: Long,
		start: LocalDateTime,
		end: LocalDateTime,
		typeList: List<PersonMeasurementType>
	): List<PersonMeasurementDto>
}
