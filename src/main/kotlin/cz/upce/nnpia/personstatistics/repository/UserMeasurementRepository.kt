package cz.upce.nnpia.personstatistics.repository

import cz.upce.nnpia.personstatistics.entity.UserMeasurement
import org.springframework.data.jpa.repository.JpaRepository

interface UserMeasurementRepository : JpaRepository<UserMeasurement, Long>
