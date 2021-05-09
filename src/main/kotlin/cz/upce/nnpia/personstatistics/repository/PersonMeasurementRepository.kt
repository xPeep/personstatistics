package cz.upce.nnpia.personstatistics.repository

import cz.upce.nnpia.personstatistics.entity.PersonMeasurement
import org.springframework.data.jpa.repository.JpaRepository

interface PersonMeasurementRepository : JpaRepository<PersonMeasurement, Long> {
}
