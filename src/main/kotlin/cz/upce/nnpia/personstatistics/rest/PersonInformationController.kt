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

	@PostMapping("/add-person-information")
	fun addPersonInformation(personInformationDto: PersonInformationDto) {
		personInformationServiceImpl.addPersonInformation(personInformationDto)
	}

	@GetMapping("/remove-person-information/{id}")
	fun removePersonInformation(@PathVariable id: Long) {
		personInformationServiceImpl.removePersonInformation(id)
	}

	@GetMapping("/get-person-information/{id}")
	fun getPersonInformation(@PathVariable id: Long): PersonInformationDto? {
		return personInformationServiceImpl.getPersonInformation(id)
	}

}
