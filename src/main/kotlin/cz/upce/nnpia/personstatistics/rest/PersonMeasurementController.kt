package cz.upce.nnpia.personstatistics.rest

import cz.upce.nnpia.personstatistics.dto.PersonMeasurementDto
import cz.upce.nnpia.personstatistics.dto.PersonMeasurementIntervalDto
import cz.upce.nnpia.personstatistics.service.implementations.PersonMeasurementServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/person-measurement")
@CrossOrigin("*")
class PersonMeasurementController
@Autowired constructor(
	private val personMeasurementServiceImpl: PersonMeasurementServiceImpl
) {

	@ExceptionHandler(RuntimeException::class)
	fun handleException(): String = "error"

	@PostMapping("/add")
	fun add(@RequestBody personMeasurementDto: PersonMeasurementDto) {
		personMeasurementServiceImpl.addMeasurement(personMeasurementDto)
	}

	@GetMapping("/remove/{personId}/{measurementId}")
	fun remove(@PathVariable personId: Long, @PathVariable measurementId: Long) {
		personMeasurementServiceImpl.removeMeasurement(personId, measurementId)
	}

	@GetMapping("/get/{personId}")
	fun all(@PathVariable personId: Long): List<PersonMeasurementDto> {
		return personMeasurementServiceImpl.getAllMeasurements(personId)
	}

	@GetMapping("/interval")
	fun interval(@RequestBody personMeasurementIntervalDto: PersonMeasurementIntervalDto): List<PersonMeasurementDto> {
		return personMeasurementServiceImpl.getMeasurementsByInterval(personMeasurementIntervalDto)
	}

}
