package cz.upce.nnpia.personstatistics.controller

import cz.upce.nnpia.personstatistics.dto.UserMeasurementDto
import cz.upce.nnpia.personstatistics.dto.UserMeasurementIntervalDto
import cz.upce.nnpia.personstatistics.service.implementations.UserMeasurementService
import cz.upce.nnpia.personstatistics.service.implementations.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/measurement")
class UserMeasurementController
@Autowired constructor(
	private val userMeasurementService: UserMeasurementService,
	private val userService: UserService
) {

	@ExceptionHandler(RuntimeException::class)
	fun handleException(): String = "error"

	@PostMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	fun add(@RequestBody userMeasurementDto: UserMeasurementDto): UserMeasurementDto {
		userMeasurementDto.userId = userService.getLoggedUserId()
		return userMeasurementService.add(userMeasurementDto)
	}

	@DeleteMapping("/{measurementId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	fun remove(@PathVariable measurementId: Long) {
		userMeasurementService.removeById(measurementId)
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	fun all(): List<UserMeasurementDto> {
		return userMeasurementService.getAll(userService.getLoggedUserId())
	}

	@PostMapping("/interval")
	@PreAuthorize("hasRole('ROLE_USER')")
	fun interval(@RequestBody userMeasurementIntervalDto: UserMeasurementIntervalDto): List<UserMeasurementDto> {
		userMeasurementIntervalDto.userId = userService.getLoggedUserId()
		return userMeasurementService.getMeasurementsByInterval(userMeasurementIntervalDto)
	}

}
