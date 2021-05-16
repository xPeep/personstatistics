package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.repository.ApplicationUserRepository
import cz.upce.nnpia.personstatistics.service.implementations.UserService
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
class ApplicationUserServiceTest
@Autowired constructor(
	private val personalMockGenerator: PersonMockGenerator,
	private val userService: UserService,
	private val applicationUserRepository: ApplicationUserRepository,
) {

	@Test
	fun addPerson() {
		val person = userService.addUser(personalMockGenerator.createPersonal())
		assertThat(person.username).isEqualTo(person.username)
	}

	@Test
	fun removePerson() {
		val person = userService.addUser(personalMockGenerator.createPersonal())
		userService.removeById(person.id ?: -1)
		val testedPerson = applicationUserRepository.findAll()
		assertTrue(testedPerson.isEmpty())
	}

	@Test
	fun getPerson() {
		val person = userService.addUser(personalMockGenerator.createPersonal())
		val testedPerson = userService.getById(person.id ?: -1)
		assertThat(person.username).isEqualTo(testedPerson.username)
	}

	@Test
	fun getAllPersons() {
		val persons = listOf(
			personalMockGenerator.createPersonal(),
			personalMockGenerator.createPersonal(),
			personalMockGenerator.createPersonal(),
			personalMockGenerator.createPersonal()
		)

		persons.forEach { person -> userService.addUser(person) }
		val testedPersons = userService.getAll()
		assertTrue(persons.map { it.username }.containsAll(testedPersons.map { it.username }))
	}
}