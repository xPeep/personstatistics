package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.repository.PersonInformationRepository
import cz.upce.nnpia.personstatistics.repository.PersonRepository
import cz.upce.nnpia.personstatistics.service.implementations.PersonInformationServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan

@DataJpaTest
@ComponentScan
class PersonInformationServiceImplTest
@Autowired constructor(
	private val personalMockGenerator: PersonMockGenerator,
	private val personRepository: PersonRepository,
	private val personInformationRepository: PersonInformationRepository,
	private val personInformationServiceImpl: PersonInformationServiceImpl,
) {

	@Test
	fun addPersonInformation() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		personRepository.save(person)
		val personInformation = personalMockGenerator.createPersonalInformation()
		personInformationServiceImpl.addPersonInformation(person.id ?: -1, personInformation)
		val foundPersonInformation = personInformationRepository.findAll().firstOrNull()?.toDtoClass()
		assertThat(personInformation.emailAddress).isEqualTo(foundPersonInformation?.emailAddress)
	}

	@Test
	fun getPersonInformation() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		personRepository.save(person)
		val personInformation = personalMockGenerator.createPersonalInformation()
		personInformationServiceImpl.addPersonInformation(person.id ?: -1, personInformation)
		val testedPersonInformation = personInformationServiceImpl.getPersonInformation(person.id ?: -1)
		assertThat(personInformation.emailAddress).isEqualTo(testedPersonInformation?.emailAddress)
	}

	@Test
	fun removePersonInformation() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		personRepository.save(person)
		val personInformation = personalMockGenerator.createPersonalInformation()
		personInformationServiceImpl.addPersonInformation(person.id ?: -1, personInformation)
		personInformationServiceImpl.removePersonInformation(person.id ?: -1)
		val foundPersonInformation = personInformationRepository.findAll()
		assertTrue(foundPersonInformation.isEmpty())
	}

}
