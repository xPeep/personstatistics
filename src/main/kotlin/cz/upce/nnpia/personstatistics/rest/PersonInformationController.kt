package cz.upce.nnpia.personstatistics.rest

import cz.upce.nnpia.personstatistics.dto.PersonInformationDto
import cz.upce.nnpia.personstatistics.service.implementations.PersonInformationServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/person-information")
@CrossOrigin("*")
class PersonInformationController
@Autowired constructor(
	private val personInformationServiceImpl: PersonInformationServiceImpl
) {

	@ExceptionHandler(RuntimeException::class)
	fun handleException(): String = "error"

	@PostMapping("/add")
	fun add(@RequestBody personInformationDto: PersonInformationDto) {
		personInformationServiceImpl.addPersonInformation(personInformationDto)
	}

	@GetMapping("/remove/{id}")
	fun remove(@PathVariable id: Long) {
		personInformationServiceImpl.removePersonInformation(id)
	}

	@GetMapping("/get/{id}")
	fun get(@PathVariable id: Long): PersonInformationDto? {
		return personInformationServiceImpl.getPersonInformation(id)
	}

}
