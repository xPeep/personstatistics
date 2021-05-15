package cz.upce.nnpia.personstatistics.service.implementations

import cz.upce.nnpia.personstatistics.dto.UserMeasurementDto
import cz.upce.nnpia.personstatistics.dto.UserMeasurementIntervalDto
import cz.upce.nnpia.personstatistics.entity.UserInformation
import cz.upce.nnpia.personstatistics.repository.UserInformationRepository
import cz.upce.nnpia.personstatistics.service.interfaces.UserMeasurementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserMeasurement
@Autowired constructor(
	private val userInformationRepository: UserInformationRepository
) : UserMeasurementService {


	private fun getUserInformation(id: Long?): UserInformation {
		return userInformationRepository.findByIdOrNull(id)
			?: throw IllegalStateException("User information was not found by id")
	}

	@Transactional
	override fun add(userMeasurementDto: UserMeasurementDto) {
		val userInformation = getUserInformation(userMeasurementDto.userId)
		userInformation.userMeasurement.add(userMeasurementDto.toEntityClass())
		userInformationRepository.save(userInformation)
	}

	@Transactional
	override fun removeById(id: Long, userMeasurementId: Long) {
		val userInformation = getUserInformation(userMeasurementId)
		userInformation.userMeasurement.removeIf { it.id == userMeasurementId }
		userInformationRepository.save(userInformation)
	}

	@Transactional
	override fun getAll(id: Long): List<UserMeasurementDto> {
		val userInformation = getUserInformation(id)
		return userInformation.userMeasurement.map { it.toDtoClass() }
	}

	@Transactional
	override fun getMeasurementsByInterval(userMeasurementIntervalDto: UserMeasurementIntervalDto): List<UserMeasurementDto> {
		return getUserInformation(userMeasurementIntervalDto.userId).userMeasurement
			.filter { userMeasurementIntervalDto.typeList.contains(it.type) }
			.filter {
				it.timeStamp?.isAfter(userMeasurementIntervalDto.start) == true && it.timeStamp?.isBefore(
					userMeasurementIntervalDto.end
				) == true
			}
			.map { it.toDtoClass() }
	}

}
