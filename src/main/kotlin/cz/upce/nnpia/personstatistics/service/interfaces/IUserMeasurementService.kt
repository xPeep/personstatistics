package cz.upce.nnpia.personstatistics.service.interfaces

import cz.upce.nnpia.personstatistics.dto.UserMeasurementDto
import cz.upce.nnpia.personstatistics.dto.UserMeasurementIntervalDto

interface IUserMeasurementService {
	fun add(userMeasurementDto: UserMeasurementDto): UserMeasurementDto
	fun removeById(userMeasurementId: Long)
	fun getAll(userId: Long): List<UserMeasurementDto>
	fun getMeasurementsByInterval(userMeasurementIntervalDto: UserMeasurementIntervalDto): List<UserMeasurementDto>
}
