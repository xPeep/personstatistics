package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.repository.PersonRepository
import cz.upce.nnpia.personstatistics.service.implementations.PersonServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ComponentScan
class PersonServiceImplTest
@Autowired constructor(
	private val personalMockGenerator: PersonMockGenerator,
	private val personServiceImpl: PersonServiceImpl,
	private val personRepository: PersonRepository,
) {

	@Test
	fun addPerson() {
		val person = personServiceImpl.addPerson(personalMockGenerator.createPersonal())
		assertThat(person.username).isEqualTo(person.username)
	}

	@Test
	fun removePerson() {
		val person = personServiceImpl.addPerson(personalMockGenerator.createPersonal())
		personServiceImpl.removePerson(person.id ?: -1)
		val testedPerson = personRepository.findAll()
		assertTrue(testedPerson.isEmpty())
	}

	@Test
	fun getPerson() {
		val person = personServiceImpl.addPerson(personalMockGenerator.createPersonal())
		val testedPerson = personServiceImpl.getPerson(person.id ?: -1)
		assertThat(person.username).isEqualTo(testedPerson?.username)
	}

	@Test
	fun getAllPersons() {
		val persons = listOf(
			personalMockGenerator.createPersonal(),
			personalMockGenerator.createPersonal(),
			personalMockGenerator.createPersonal(),
			personalMockGenerator.createPersonal()
		)

		persons.forEach { person -> personServiceImpl.addPerson(person) }
		val testedPersons = personServiceImpl.getAllPersons()
		assertTrue(persons.map { it.username }.containsAll(testedPersons.map { it.username }))
	}
}
