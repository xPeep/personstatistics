package cz.upce.nnpia.personstatistics.service.implementations

import cz.upce.nnpia.personstatistics.dto.UserMeasurementDto
import cz.upce.nnpia.personstatistics.dto.UserMeasurementIntervalDto
import cz.upce.nnpia.personstatistics.repository.UserMeasurementRepository
import cz.upce.nnpia.personstatistics.service.interfaces.IUserMeasurementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserMeasurementService
@Autowired constructor(
	private val userMeasurementRepository: UserMeasurementRepository,
	private val userService: UserService
) : IUserMeasurementService {

	override fun add(userMeasurementDto: UserMeasurementDto) {
		val user = userService.getUserById(userMeasurementDto.userId ?: -1)
		userMeasurementRepository.save(userMeasurementDto.toEntityClass(user))
	}

	override fun removeById(userMeasurementId: Long) {
		userMeasurementRepository.deleteById(userMeasurementId)
	}

	override fun getAll(userId: Long): List<UserMeasurementDto> {
		return userMeasurementRepository.findByApplicationUserId(userId).map { it.toDtoClass() }
	}

	override fun getMeasurementsByInterval(userMeasurementIntervalDto: UserMeasurementIntervalDto): List<UserMeasurementDto> {
		return userMeasurementRepository.findByApplicationUserAndInterval(
			userMeasurementIntervalDto.userId,
			userMeasurementIntervalDto.start,
			userMeasurementIntervalDto.end
		).map { it.toDtoClass() }
	}

}
