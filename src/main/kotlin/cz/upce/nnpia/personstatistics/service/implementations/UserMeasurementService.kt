package cz.upce.nnpia.personstatistics.service.implementations

import cz.upce.nnpia.personstatistics.dto.UserMeasurementDto
import cz.upce.nnpia.personstatistics.dto.UserMeasurementIntervalDto
import cz.upce.nnpia.personstatistics.entity.ApplicationUser
import cz.upce.nnpia.personstatistics.repository.ApplicationUserRepository
import cz.upce.nnpia.personstatistics.repository.UserMeasurementRepository
import cz.upce.nnpia.personstatistics.service.interfaces.UserMeasurementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserMeasurementService
@Autowired constructor(
	private val userMeasurementRepository: UserMeasurementRepository
) : UserMeasurementService {

	override fun add(userMeasurementDto: UserMeasurementDto) {
		userMeasurementRepository.save(userMeasurementDto.toEntityClass())
	}

	override fun removeById(userMeasurementId: Long) {
		userMeasurementRepository.deleteById(userMeasurementId)
	}

	override fun getAll(id: Long): List<UserMeasurementDto> {
		return userMeasurementRepository.findByApplicationUserId(id).map { it.toDtoClass()}
	}

	override fun getMeasurementsByInterval(userMeasurementIntervalDto: UserMeasurementIntervalDto): List<UserMeasurementDto> {
		return userMeasurementRepository.findByApplicationUserAndInterval(
			userMeasurementIntervalDto.userId,
			userMeasurementIntervalDto.start,
			userMeasurementIntervalDto.end
		).map { it.toDtoClass()}
	}

}
