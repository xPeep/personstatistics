package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.dto.UserMeasurementIntervalDto
import cz.upce.nnpia.personstatistics.repository.ApplicationUserRepository
import cz.upce.nnpia.personstatistics.repository.UserMeasurementRepository
import cz.upce.nnpia.personstatistics.repository.UserPhotoRepository
import cz.upce.nnpia.personstatistics.service.implementations.UserMeasurementService
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
class ApplicationUserMeasurementServiceTest
@Autowired constructor(
	private val personalMockGenerator: PersonMockGenerator,
	private val applicationUserRepository: ApplicationUserRepository,
	private val userMeasurementRepository: UserMeasurementRepository,
	private val userMeasurementService: UserMeasurementService,
	private val userPhotoRepository: UserPhotoRepository
) {

	@BeforeEach
	fun clean() {
		userPhotoRepository.deleteAll()
		userMeasurementRepository.deleteAll()
		applicationUserRepository.deleteAll()
	}

	@Test
	fun addMeasurement() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		applicationUserRepository.save(person)
		val personMeasurement = personalMockGenerator.createPersonalMeasurement()
		personMeasurement.userId = person.id ?: -1
		userMeasurementService.add(personMeasurement)
		val foundMeasurement = userMeasurementRepository.findAll().firstOrNull()?.toDtoClass()
		assertThat(personMeasurement.leftHandSize).isEqualTo(foundMeasurement?.leftHandSize)
	}

	@Test
	fun removeMeasurement() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		applicationUserRepository.save(person)

		val personMeasurements = listOf(
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement()
		)

		personMeasurements.forEach { personMeasurement ->
			personMeasurement.userId = person.id ?: -1
			userMeasurementService.add(personMeasurement)
		}

		val foundMeasurement = userMeasurementService.getAll(person.id ?: -1)

		foundMeasurement.forEach { personMeasurement ->
			userMeasurementService.removeById(personMeasurement.id ?: -1)
		}

		val testedMeasurement = userMeasurementService.getAll(person.id ?: -1)

		assertTrue(testedMeasurement.isEmpty())
	}

	@Test
	fun getAllMeasurements() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		applicationUserRepository.save(person)

		val personMeasurements = listOf(
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement()
		)

		personMeasurements.forEach { personMeasurement ->
			personMeasurement.userId = person.id
			userMeasurementService.add(personMeasurement)
		}

		val foundMeasurement = userMeasurementService.getAll(person.id ?: -1)

		assertTrue(foundMeasurement.map { it.leftHandSize }.containsAll(personMeasurements.map { it.leftHandSize }))
	}

	@Test
	fun getMeasurementsByInterval() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		applicationUserRepository.save(person)

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
			userMeasurementService.add(personMeasurement)
		}

		val foundMeasurement = userMeasurementService.getMeasurementsByInterval(
			UserMeasurementIntervalDto(
				person.id ?: -1,
				startDate,
				endDate
			)
		)

		assertTrue(foundMeasurement.map { it.timestamp }
			.containsAll(testedPersonMeasurements.map { it.timestamp }))

		val foundMeasurementNone = userMeasurementService.getMeasurementsByInterval(
			UserMeasurementIntervalDto(
				-1,
				startDate,
				endDate
			)
		)

		assertTrue(foundMeasurementNone.isEmpty())
	}
}
