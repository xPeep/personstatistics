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

	@PostMapping("/add")
	fun add(@RequestBody personDto: PersonDto) {
		personServiceImpl.addPerson(personDto)
	}

	@GetMapping("/remove/{id}")
	fun remove(@PathVariable id: Long) {
		personServiceImpl.removePerson(id)
	}

	@GetMapping("/get/{id}")
	fun get(@PathVariable id: Long): PersonDto? {
		return personServiceImpl.getPerson(id)
	}

	@GetMapping("/all")
	fun all(): List<PersonDto> {
		return personServiceImpl.getAllPersons()
	}

}
