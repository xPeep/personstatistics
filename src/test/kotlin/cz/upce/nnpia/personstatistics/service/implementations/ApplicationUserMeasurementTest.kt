package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.dto.UserMeasurementIntervalDto
import cz.upce.nnpia.personstatistics.entity.UserMeasurementType
import cz.upce.nnpia.personstatistics.repository.ApplicationUserRepository
import cz.upce.nnpia.personstatistics.repository.UserMeasurementRepository
import cz.upce.nnpia.personstatistics.service.implementations.UserInformation
import cz.upce.nnpia.personstatistics.service.implementations.UserMeasurement
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
class ApplicationUserMeasurementTest
@Autowired constructor(
	private val personalMockGenerator: PersonMockGenerator,
	private val applicationUserRepository: ApplicationUserRepository,
	private val userMeasurementRepository: UserMeasurementRepository,
	private val userMeasurement: UserMeasurement,
	private val userInformation: UserInformation,
) {

	@Test
	fun addMeasurement() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		applicationUserRepository.save(person)
		val personInformation = personalMockGenerator.createPersonalInformation(person.id ?: -1)
		userInformation.add(personInformation)
		val personMeasurement = personalMockGenerator.createPersonalMeasurement()
		personMeasurement.userId = person.id ?: -1
		userMeasurement.add(personMeasurement)
		val foundMeasurement = userMeasurementRepository.findAll().firstOrNull()?.toDtoClass()
		assertThat(personMeasurement.value).isEqualTo(foundMeasurement?.value)
	}

	@Test
	fun removeMeasurement() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		applicationUserRepository.save(person)
		val personInformation = personalMockGenerator.createPersonalInformation(person.id ?: -1)
		userInformation.add(personInformation)

		val personMeasurements = listOf(
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement()
		)

		personMeasurements.forEach { personMeasurement ->
			personMeasurement.userId = person.id ?: -1
			userMeasurement.add(personMeasurement)
		}

		val foundMeasurement = userMeasurement.getAll(person.id ?: -1)

		foundMeasurement.forEach { personMeasurement ->
			userMeasurement.removeById(person.id ?: -1, personMeasurement.id ?: -1)
		}

		val testedMeasurement = userMeasurement.getAll(person.id ?: -1)

		assertTrue(testedMeasurement.isEmpty())
	}

	@Test
	fun getAllMeasurements() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		applicationUserRepository.save(person)
		val personInformation = personalMockGenerator.createPersonalInformation(person.id ?: -1)
		userInformation.add(personInformation)

		val personMeasurements = listOf(
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement()
		)

		personMeasurements.forEach { personMeasurement ->
			personMeasurement.userId = person.id ?: -1
			userMeasurement.add(personMeasurement)
		}

		val foundMeasurement = userMeasurement.getAll(person.id ?: -1)

		assertTrue(personMeasurements.map { it.value }.containsAll(foundMeasurement.map { it.value }))
	}

	@Test
	fun getMeasurementsByInterval() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		applicationUserRepository.save(person)
		val personInformation = personalMockGenerator.createPersonalInformation(person.id ?: -1)
		userInformation.add(personInformation)

		val startDate = personalMockGenerator.createLocalDateTimeFromString("2004-31-12 23:59")
		val endDate = personalMockGenerator.createLocalDateTimeFromString("2015-01-01 00:01")

		val personMeasurements = listOf(
			personalMockGenerator.createPersonalMeasurement("2000-01-01 00:00"),
			personalMockGenerator.createPersonalMeasurement("2005-01-01 00:00"),
			personalMockGenerator.createPersonalMeasurement("2010-01-01 00:00"),
			personalMockGenerator.createPersonalMeasurement("2015-01-01 00:00"),
			personalMockGenerator.createPersonalMeasurement("2020-01-01 00:00")
		)

		val testedPersonMeasurements = listOf(
			personalMockGenerator.createPersonalMeasurement("2005-01-01 00:00"),
			personalMockGenerator.createPersonalMeasurement("2010-01-01 00:00"),
			personalMockGenerator.createPersonalMeasurement("2015-01-01 00:00")
		)

		personMeasurements.forEach { personMeasurement ->
			personMeasurement.userId = person.id ?: -1
			userMeasurement.add(personMeasurement)
		}

		val foundMeasurement = userMeasurement.getMeasurementsByInterval(
			UserMeasurementIntervalDto(
				person.id ?: -1,
				startDate,
				endDate,
				UserMeasurementType.values().toList()
			)
		)

		assertTrue(testedPersonMeasurements.map { it.value }.containsAll(foundMeasurement.map { it.value }))

		val foundMeasurementNone = userMeasurement.getMeasurementsByInterval(
			UserMeasurementIntervalDto(
				person.id ?: -1,
				startDate,
				endDate,
				listOf(UserMeasurementType.NONE)
			)
		)

		assertTrue(foundMeasurementNone.isEmpty())
	}
}
