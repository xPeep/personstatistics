package cz.upce.nnpia.personstatistics.rest

import cz.upce.nnpia.personstatistics.dto.UserInformationDto
import cz.upce.nnpia.personstatistics.service.implementations.UserInformation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/user/information")
@CrossOrigin("*")
class UserInformationController
@Autowired constructor(
	private val userInformation: UserInformation
) {

	@ExceptionHandler(RuntimeException::class)
	fun handleException(): String = "error"

	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	fun add(@RequestBody userInformationDto: UserInformationDto) {
		userInformation.add(userInformationDto)
	}

	@GetMapping("/remove/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	fun remove(@PathVariable id: Long) {
		userInformation.removeById(id)
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	fun get(@PathVariable id: Long): UserInformationDto? {
		return userInformation.getById(id)
	}

}
