package cz.upce.nnpia.personstatistics.service.interfaces

import cz.upce.nnpia.personstatistics.dto.UserMeasurementDto
import cz.upce.nnpia.personstatistics.dto.UserMeasurementIntervalDto

interface UserMeasurementService {
	fun add(userMeasurementDto: UserMeasurementDto)
	fun removeById(id: Long, userMeasurementId: Long)
	fun getAll(id: Long): List<UserMeasurementDto>
	fun getMeasurementsByInterval(userMeasurementIntervalDto: UserMeasurementIntervalDto): List<UserMeasurementDto>
}
