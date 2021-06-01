package cz.upce.nnpia.personstatistics.controller

import cz.upce.nnpia.personstatistics.dto.GetUserDto
import cz.upce.nnpia.personstatistics.dto.UserDto
import cz.upce.nnpia.personstatistics.logging.LoggingUtility
import cz.upce.nnpia.personstatistics.security.UserRole
import cz.upce.nnpia.personstatistics.service.implementations.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/user")
class UserController
@Autowired constructor(
	private val userService: UserService
) {
	private val logger = LoggingUtility.getLogger()

	@ExceptionHandler(RuntimeException::class)
	fun handleException(): String = "error"


	@PostMapping
	@Throws(Exception::class)
	fun add(@RequestBody userDto: UserDto): ResponseEntity<Any> {
		logger.info("Add user..")
		userDto.role = UserRole.USER
		return try {
			userService.addUser(userDto)
			ResponseEntity.ok(true)
		} catch (e: RuntimeException) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot add user")
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	fun remove(@PathVariable id: Long) {
		logger.info("Remove user..ADMIN_ROLE")
		val person = userService.getById(id)
		if (userService.getLoggedUserId() != person.id) {
			userService.removeById(id)
		} else {
			throw IllegalStateException("Impossible delete admin account")
		}
	}

	@DeleteMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	fun remove() {
		logger.info("Remove user..ROLE_USER")
		userService.removeById(userService.getLoggedUserId())
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	fun get(@PathVariable id: Long): UserDto? {
		logger.info("Get user..ROLE_ADMIN")
		return userService.getById(id)
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	fun get(): GetUserDto? {
		logger.info("Get user..ROLE_USER and ROLE_ADMIN")
		val user = userService.getById(userService.getLoggedUserId())
		return GetUserDto(user.id, user.username, user.password, user.firstName, user.lastName, user.emailAddress)
	}

	@GetMapping("/all")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	fun all(): List<UserDto> {
		logger.info("Getting all users..")
		return userService.getAll()
	}

}
