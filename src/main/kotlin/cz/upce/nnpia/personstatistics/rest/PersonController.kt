package cz.upce.nnpia.personstatistics.rest

import cz.upce.nnpia.personstatistics.dto.PersonDto
import cz.upce.nnpia.personstatistics.service.implementations.PersonServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/person")
@CrossOrigin("*")
class PersonController
@Autowired constructor(
	private val personServiceImpl: PersonServiceImpl
) {

	@ExceptionHandler(RuntimeException::class)
	fun handleException(): String = "error"

	@PostMapping("/add-person")
	fun addPerson(personDto: PersonDto) {
		personServiceImpl.addPerson(personDto)
	}

	@GetMapping("/remove-person/{id}")
	fun removePerson(@PathVariable id: Long) {
		personServiceImpl.removePerson(id)
	}

	@GetMapping("/get-person/{id}")
	fun getPerson(@PathVariable id: Long): PersonDto? {
		return personServiceImpl.getPerson(id)
	}

	@GetMapping("/get-all-persons")
	fun getAllPersons(@PathVariable id: Long): List<PersonDto> {
		return personServiceImpl.getAllPersons()
	}

}
