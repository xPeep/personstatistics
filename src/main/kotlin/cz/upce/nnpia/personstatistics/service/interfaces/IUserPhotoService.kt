package cz.upce.nnpia.personstatistics.service.interfaces

import cz.upce.nnpia.personstatistics.dto.UserPhotoDto
import org.springframework.web.multipart.MultipartFile

interface IUserPhotoService {
	fun add(userPhotoDto: UserPhotoDto)
	fun removeById(userPhotoDtoId: Long)
	fun getAll(userId: Long): List<UserPhotoDto>
}
