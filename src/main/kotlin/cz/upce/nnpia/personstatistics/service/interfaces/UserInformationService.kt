package cz.upce.nnpia.personstatistics.service.interfaces

import cz.upce.nnpia.personstatistics.dto.UserInformationDto

interface UserInformationService {
	fun add(userInformationDto: UserInformationDto)
	fun getById(id: Long): UserInformationDto
	fun removeById(id: Long)
}
