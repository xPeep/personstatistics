package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.repository.ApplicationUserRepository
import cz.upce.nnpia.personstatistics.repository.UserInformationRepository
import cz.upce.nnpia.personstatistics.service.implementations.UserInformation
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
class ApplicationUserInformationTest
@Autowired constructor(
	private val personalMockGenerator: PersonMockGenerator,
	private val applicationUserRepository: ApplicationUserRepository,
	private val userInformationRepository: UserInformationRepository,
	private val userInformation: UserInformation,
) {

	@Test
	fun addPersonInformation() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		applicationUserRepository.save(person)
		val personInformation = personalMockGenerator.createPersonalInformation(person.id ?: -1)
		userInformation.add(personInformation)
		val foundPersonInformation = userInformationRepository.findAll().firstOrNull()?.toDtoClass()
		assertThat(personInformation.emailAddress).isEqualTo(foundPersonInformation?.emailAddress)
	}

	@Test
	fun getPersonInformation() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		applicationUserRepository.save(person)
		val personInformation = personalMockGenerator.createPersonalInformation(person.id ?: -1)
		userInformation.add(personInformation)
		val testedPersonInformation = userInformation.getById(person.id ?: -1)
		assertThat(personInformation.emailAddress).isEqualTo(testedPersonInformation.emailAddress)
	}

	@Test
	fun removePersonInformation() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		applicationUserRepository.save(person)
		val personInformation = personalMockGenerator.createPersonalInformation(person.id ?: -1)
		userInformation.add(personInformation)
		userInformation.removeById(person.id ?: -1)
		val foundPersonInformation = userInformationRepository.findAll()
		assertTrue(foundPersonInformation.isEmpty())
	}

}
