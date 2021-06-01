package cz.upce.nnpia.personstatistics.service.implementations

import cz.upce.nnpia.personstatistics.dto.UserPhotoDto
import cz.upce.nnpia.personstatistics.repository.UserPhotoRepository
import cz.upce.nnpia.personstatistics.service.interfaces.IUserPhotoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserPhotoService
@Autowired constructor(
	private val userPhotoRepository: UserPhotoRepository,
	private val userService: UserService
) : IUserPhotoService {

	override fun add(userPhotoDto: UserPhotoDto) {
		val user = userService.getUserById(userPhotoDto.userId ?: -1)
		userPhotoRepository.save(userPhotoDto.toEntityClass(user))
	}

	override fun removeById(userPhotoDtoId: Long) {
		userPhotoRepository.deleteById(userPhotoDtoId)
	}

	override fun getAll(userId: Long): List<UserPhotoDto> {
		return userPhotoRepository.findByApplicationUserId(userId).map { it.toDtoClass() }
	}

	fun get(loggedUserId: Long): UserPhotoDto{
		return getAll(loggedUserId).first()
	}

}
