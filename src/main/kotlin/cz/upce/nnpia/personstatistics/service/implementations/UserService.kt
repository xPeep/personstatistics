package cz.upce.nnpia.personstatistics.service.implementations

import cz.upce.nnpia.personstatistics.auth.IAuthenticationFacade
import cz.upce.nnpia.personstatistics.dto.UserDto
import cz.upce.nnpia.personstatistics.entity.ApplicationUser
import cz.upce.nnpia.personstatistics.repository.ApplicationUserRepository
import cz.upce.nnpia.personstatistics.service.interfaces.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService
@Autowired constructor(
	private val applicationUserRepository: ApplicationUserRepository,
	private val passwordEncoder: PasswordEncoder,
	private val authenticationFacade: IAuthenticationFacade
) : IUserService, UserDetailsService {

	fun getUserById(userId: Long): ApplicationUser {
		return applicationUserRepository.findByIdOrNull(userId)
			?: throw java.lang.IllegalStateException("User by id not found")
	}

	fun getLoggedUserId(): Long {
		return getByUsername(authenticationFacade.getUserName()).id
			?: throw java.lang.IllegalStateException("Logged user is not found")
	}

	override fun addUser(userDto: UserDto): UserDto {
		val user = userDto.toEntityClass()
		applicationUserRepository.save(user)
		return user.toDtoClass()
	}

	override fun removeById(id: Long) {
		applicationUserRepository.deleteById(id)
	}

	override fun getById(id: Long): UserDto {
		return (applicationUserRepository.findByIdOrNull(id)
			?: throw IllegalStateException("User by id not found")).toDtoClass()
	}

	override fun getByUsername(username: String): UserDto {
		return (applicationUserRepository.findByNickname(username)
			?: throw IllegalStateException("User by username not found")).toDtoClass()
	}

	override fun getAll(): List<UserDto> {
		return applicationUserRepository.findAll().map { it.toDtoClass() }
	}

	override fun loadUserByUsername(username: String): UserDetails {
		val userDetails = applicationUserRepository.findByNickname(username)
			?: throw IllegalStateException("User by username not found")
		userDetails.userPassword = passwordEncoder.encode(userDetails.password)
		return userDetails
	}
}
