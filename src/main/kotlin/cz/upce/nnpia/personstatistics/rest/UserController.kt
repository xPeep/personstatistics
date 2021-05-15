package cz.upce.nnpia.personstatistics.rest

import cz.upce.nnpia.personstatistics.auth.IAuthenticationFacade
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
	private val userService: UserService,
	private val authenticationFacade: IAuthenticationFacade
) {

	@ExceptionHandler(RuntimeException::class)
	fun handleException(): String = "error"

	fun getUserId(): Long {
		return userService.getByUsername(authenticationFacade.getUserName()).id
			?: throw IllegalStateException("User id is not exist")
	}

	@PostMapping("/add")
	fun add(@RequestBody userDto: UserDto) {
		userDto.role = UserRole.USER
		userService.addUser(userDto)
	}

	@GetMapping("/remove/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	fun remove(@PathVariable id: Long) {
		val person = userService.getById(id)
		if (authenticationFacade.getUserName() != person.username) {
			userService.removeById(id)
		} else {
			throw IllegalStateException("Impossible delete admin account")
		}
	}

	@GetMapping("/remove")
	@PreAuthorize("hasRole('ROLE_USER')")
	fun remove() {
		userService.removeById(getUserId())
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	fun get(@PathVariable id: Long): UserDto? {
		return userService.getById(id)
	}

	@GetMapping("/get")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	fun get(): UserDto? {
		return userService.getById(getUserId())
	}

	@GetMapping("/all")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	fun all(): List<UserDto> {
		return userService.getAll()
	}

}
