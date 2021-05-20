package cz.upce.nnpia.personstatistics.rest

import cz.upce.nnpia.personstatistics.dto.UserMeasurementDto
import cz.upce.nnpia.personstatistics.dto.UserMeasurementIntervalDto
import cz.upce.nnpia.personstatistics.service.implementations.UserMeasurementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/user/measurement")
@CrossOrigin("*")
class UserMeasurementController
@Autowired constructor(
	private val userMeasurementService: UserMeasurementService
) {

	@ExceptionHandler(RuntimeException::class)
	fun handleException(): String = "error"

	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_USER')")
	fun add(@RequestBody userMeasurementDto: UserMeasurementDto) {
		userMeasurementService.add(userMeasurementDto)
	}

	@GetMapping("/remove/{personId}/{measurementId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	fun remove(@PathVariable measurementId: Long) {
		userMeasurementService.removeById(measurementId)
	}

	@GetMapping("/get/{personId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	fun all(@PathVariable personId: Long): List<UserMeasurementDto> {
		return userMeasurementService.getAll(personId)
	}

	@GetMapping("/interval")
	@PreAuthorize("hasRole('ROLE_USER')")
	fun interval(@RequestBody userMeasurementIntervalDto: UserMeasurementIntervalDto): List<UserMeasurementDto> {
		return userMeasurementService.getMeasurementsByInterval(userMeasurementIntervalDto)
	}

}
