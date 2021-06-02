package cz.upce.nnpia.personstatistics.service.interfaces

import cz.upce.nnpia.personstatistics.dto.UserPhotoDto

interface IUserPhotoService {
	fun add(userPhotoDto: UserPhotoDto)
	fun removeById(userPhotoDtoId: Long)
	fun getAll(userId: Long): List<UserPhotoDto>
}
