package cz.upce.nnpia.personstatistics.rest

import cz.upce.nnpia.personstatistics.dto.PersonMeasurementDto
import cz.upce.nnpia.personstatistics.entity.PersonMeasurementType
import cz.upce.nnpia.personstatistics.service.implementations.PersonMeasurementServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("api/person-measurement")
@CrossOrigin("*")
class PersonMeasurementController
@Autowired constructor(
	private val personMeasurementServiceImpl: PersonMeasurementServiceImpl
) {

	@ExceptionHandler(RuntimeException::class)
	fun handleException(): String = "error"

	@RequestMapping(path = ["/add"], method = [RequestMethod.GET])
	fun add(@RequestParam personId: Long, @RequestParam personMeasurementDto: PersonMeasurementDto) {
		personMeasurementServiceImpl.addMeasurement(personId, personMeasurementDto)
	}

	@GetMapping("/remove/{personId}/{measurementId}")
	fun remove(@PathVariable personId: Long, @PathVariable measurementId: Long) {
		personMeasurementServiceImpl.removeMeasurement(personId, measurementId)
	}

	@GetMapping("/get/{personId}")
	fun all(@PathVariable personId: Long): List<PersonMeasurementDto> {
		return personMeasurementServiceImpl.getAllMeasurements(personId)
	}

	@RequestMapping(path = ["/interval"], method = [RequestMethod.GET])
	fun interval(
		@RequestParam personId: Long,
		@RequestParam start: LocalDateTime,
		@RequestParam end: LocalDateTime,
		@RequestParam type: List<PersonMeasurementType>
	): List<PersonMeasurementDto> {
		return personMeasurementServiceImpl.getMeasurementsByInterval(personId, start, end, type)
	}

}
