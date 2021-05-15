package cz.upce.nnpia.personstatistics.service.implementations

import cz.upce.nnpia.personstatistics.dto.UserInformationDto
import cz.upce.nnpia.personstatistics.repository.ApplicationUserRepository
import cz.upce.nnpia.personstatistics.repository.UserInformationRepository
import cz.upce.nnpia.personstatistics.service.interfaces.UserInformationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserInformation
@Autowired constructor(
	private val applicationUserRepository: ApplicationUserRepository,
	private val userInformationRepository: UserInformationRepository
) : UserInformationService {

	@Transactional
	override fun add(userInformationDto: UserInformationDto) {
		val userInformation = userInformationDto.toEntityClass()
		userInformation.applicationUser = applicationUserRepository.findByIdOrNull(userInformationDto.id ?: -1)
		userInformationRepository.save(userInformation)
	}


	@Transactional
	override fun getById(id: Long): UserInformationDto {
		return (userInformationRepository.findByIdOrNull(id)
			?: throw IllegalStateException("Person was not found by id")).toDtoClass()
	}


	@Transactional
	override fun removeById(id: Long) {
		userInformationRepository.deleteById(id)
	}
}
