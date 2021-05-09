package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.repository.PersonRepository
import cz.upce.nnpia.personstatistics.service.implementations.PersonServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan

@DataJpaTest
@ComponentScan
class PersonServiceImplTest
@Autowired constructor(
	private val personalMockGenerator: PersonMockGenerator,
	private val personServiceImpl: PersonServiceImpl,
	private val personRepository: PersonRepository,
) {

	@Test
	fun addPerson() {
		val person = personalMockGenerator.createPersonal().toDtoClass()
		personServiceImpl.addPerson(person)
		val testedPerson = personRepository.findAll().firstOrNull()?.toDtoClass()
		assertThat(person.username).isEqualTo(testedPerson?.username)
	}

	@Test
	fun removePerson() {
		val person = personalMockGenerator.createPersonal().toDtoClass()
		personServiceImpl.addPerson(person)
		val foundPerson = personRepository.findAll().firstOrNull()?.toDtoClass()
		personServiceImpl.removePerson(foundPerson?.id ?: -1)
		val testedPerson = personRepository.findAll()
		assertTrue(testedPerson.isEmpty())
	}

	@Test
	fun getPerson() {
		val person = personalMockGenerator.createPersonal().toDtoClass()
		personServiceImpl.addPerson(person)
		val foundPerson = personRepository.findAll()[0].toDtoClass()
		val testedPerson = personServiceImpl.getPerson(foundPerson.id)
		assertThat(person.username).isEqualTo(testedPerson?.username)
	}

	@Test
	fun getAllPersons() {
		val persons = listOf(
			personalMockGenerator.createPersonal().toDtoClass(),
			personalMockGenerator.createPersonal().toDtoClass(),
			personalMockGenerator.createPersonal().toDtoClass(),
			personalMockGenerator.createPersonal().toDtoClass()
		)
		persons.forEach { person -> personServiceImpl.addPerson(person) }
		val testedPersons = personServiceImpl.getAllPersons()
		assertTrue(persons.map { it.username }.containsAll(testedPersons.map { it.username }))
	}
}
