package cz.upce.nnpia.personstatistics.service.interfaces

import cz.upce.nnpia.personstatistics.dto.UserDto


interface IUserService {
	fun addUser(userDto: UserDto): UserDto
	fun removeById(id: Long)
	fun getById(id: Long): UserDto?
	fun getByUsername(username: String): UserDto?
	fun getAll(): List<UserDto>
}
