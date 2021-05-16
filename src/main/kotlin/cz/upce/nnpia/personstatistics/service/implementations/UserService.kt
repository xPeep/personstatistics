package cz.upce.nnpia.personstatistics.service.implementations

import cz.upce.nnpia.personstatistics.auth.CustomUserDetails
import cz.upce.nnpia.personstatistics.dto.UserDto
import cz.upce.nnpia.personstatistics.repository.ApplicationUserRepository
import cz.upce.nnpia.personstatistics.service.interfaces.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService
@Autowired constructor(
	private val applicationUserRepository: ApplicationUserRepository,
	private val passwordEncoder: PasswordEncoder
) : IUserService, UserDetailsService {

	@Transactional
	override fun addUser(userDto: UserDto): UserDto {
		val user = userDto.toEntityClass()
		applicationUserRepository.save(user)
		return user.toDtoClass()
	}

	@Transactional
	override fun removeById(id: Long) {
		applicationUserRepository.deleteById(id)
	}

	@Transactional
	override fun getById(id: Long): UserDto {
		return (applicationUserRepository.findByIdOrNull(id)
			?: throw IllegalStateException("User by id not found")).toDtoClass()
	}

	@Transactional
	override fun getByUsername(username: String): UserDto {
		return (applicationUserRepository.findByUsername(username)
			?: throw IllegalStateException("User by username not found")).toDtoClass()
	}

	@Transactional
	override fun getAll(): List<UserDto> {
		return applicationUserRepository.findAll().map { it.toDtoClass() }
	}

	override fun loadUserByUsername(username: String): UserDetails {
		val userDetails = applicationUserRepository.findByUsername(username)
			?: throw IllegalStateException("User by username not found")
		userDetails.password = passwordEncoder.encode(userDetails.password)
		return CustomUserDetails(userDetails)
	}
}
