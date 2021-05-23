package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.repository.ApplicationUserRepository
import cz.upce.nnpia.personstatistics.repository.UserMeasurementRepository
import cz.upce.nnpia.personstatistics.repository.UserPhotoRepository
import cz.upce.nnpia.personstatistics.service.implementations.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@ComponentScan
class ApplicationUserServiceTest
@Autowired constructor(
	private val personalMockGenerator: PersonMockGenerator,
	private val userService: UserService,
	private val applicationUserRepository: ApplicationUserRepository,
	private val userPhotoRepository: UserPhotoRepository,
	private val userMeasurementRepository: UserMeasurementRepository
) {

	@BeforeEach
	fun clean() {
		userPhotoRepository.deleteAll()
		userMeasurementRepository.deleteAll()
		applicationUserRepository.deleteAll()
	}

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
		assertTrue(testedPersons.map { it.username }.containsAll(persons.map { it.username }))
	}
}
