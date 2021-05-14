package cz.upce.nnpia.personstatistics.service.interfaces

import cz.upce.nnpia.personstatistics.dto.PersonMeasurementDto
import cz.upce.nnpia.personstatistics.dto.PersonMeasurementIntervalDto

interface PersonMeasurementService {
	fun addMeasurement(personMeasurementDto: PersonMeasurementDto)
	fun removeMeasurement(personId: Long, personMeasurementId: Long)
	fun getAllMeasurements(personId: Long): List<PersonMeasurementDto>
	fun getMeasurementsByInterval(personMeasurementIntervalDto: PersonMeasurementIntervalDto): List<PersonMeasurementDto>
}
