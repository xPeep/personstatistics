package cz.upce.nnpia.personstatistics.controller

import cz.upce.nnpia.personstatistics.dto.UserDto
import cz.upce.nnpia.personstatistics.security.UserRole
import cz.upce.nnpia.personstatistics.service.implementations.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/user")
@CrossOrigin("*")
class UserController
@Autowired constructor(
	private val userService: UserService
) {

	@ExceptionHandler(RuntimeException::class)
	fun handleException(): String = "error"

	@PostMapping
	fun add(@RequestBody userDto: UserDto) {
		userDto.role = UserRole.USER
		userService.addUser(userDto)
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	fun remove(@PathVariable id: Long) {
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
		userService.removeById(userService.getLoggedUserId())
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	fun get(@PathVariable id: Long): UserDto? {
		return userService.getById(id)
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	fun get(): UserDto? {
		return userService.getById(userService.getLoggedUserId())
	}

	@GetMapping("/all")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	fun all(): List<UserDto> {
		return userService.getAll()
	}

}
