package cz.upce.nnpia.personstatistics.service

import cz.upce.nnpia.personstatistics.entity.PersonMeasurementType

interface PersonMeasurementService {
	fun addMeasurement(personId: Long, value: Double, type: PersonMeasurementType)
	fun editMeasurement(personId: Long, personMeasurementId: Long, value: Double)
	fun removeMeasurement(personId: Long, personMeasurementId: Long)
}
