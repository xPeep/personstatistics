package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.dto.PersonMeasurementIntervalDto
import cz.upce.nnpia.personstatistics.entity.PersonMeasurementType
import cz.upce.nnpia.personstatistics.repository.PersonMeasurementRepository
import cz.upce.nnpia.personstatistics.repository.PersonRepository
import cz.upce.nnpia.personstatistics.service.implementations.PersonMeasurementServiceImpl
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
class PersonMeasurementServiceImplTest
@Autowired constructor(
	private val personalMockGenerator: PersonMockGenerator,
	private val personRepository: PersonRepository,
	private val personMeasurementRepository: PersonMeasurementRepository,
	private val personMeasurementServiceImpl: PersonMeasurementServiceImpl,
) {

	@Test
	fun addMeasurement() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		personRepository.save(person)
		val personMeasurement = personalMockGenerator.createPersonalMeasurement()
		personMeasurement.personId = person.id ?: -1
		personMeasurementServiceImpl.addMeasurement(personMeasurement)
		val foundMeasurement = personMeasurementRepository.findAll().firstOrNull()?.toDtoClass()
		assertThat(personMeasurement.value).isEqualTo(foundMeasurement?.value)
	}

	@Test
	fun removeMeasurement() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		personRepository.save(person)

		val personMeasurements = listOf(
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement()
		)

		personMeasurements.forEach { personMeasurement ->
			personMeasurement.personId = person.id ?: -1
			personMeasurementServiceImpl.addMeasurement(personMeasurement)
		}

		val foundMeasurement = personMeasurementServiceImpl.getAllMeasurements(person.id ?: -1)

		foundMeasurement.forEach { personMeasurement ->
			personMeasurementServiceImpl.removeMeasurement(person.id ?: -1, personMeasurement.id ?: -1)
		}

		val testedMeasurement = personMeasurementServiceImpl.getAllMeasurements(person.id ?: -1)

		assertTrue(testedMeasurement.isEmpty())
	}

	@Test
	fun getAllMeasurements() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		personRepository.save(person)

		val personMeasurements = listOf(
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement(),
			personalMockGenerator.createPersonalMeasurement()
		)

		personMeasurements.forEach { personMeasurement ->
			personMeasurement.personId = person.id ?: -1
			personMeasurementServiceImpl.addMeasurement(personMeasurement)
		}

		val foundMeasurement = personMeasurementServiceImpl.getAllMeasurements(person.id ?: -1)

		assertTrue(personMeasurements.map { it.value }.containsAll(foundMeasurement.map { it.value }))
	}

	@Test
	fun getMeasurementsByInterval() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		personRepository.save(person)

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
			personMeasurement.personId = person.id ?: -1
			personMeasurementServiceImpl.addMeasurement(personMeasurement)
		}

		val foundMeasurement = personMeasurementServiceImpl.getMeasurementsByInterval(
			PersonMeasurementIntervalDto(
				person.id ?: -1,
				startDate,
				endDate,
				PersonMeasurementType.values().toList()
			)
		)

		assertTrue(testedPersonMeasurements.map { it.value }.containsAll(foundMeasurement.map { it.value }))

		val foundMeasurementNone = personMeasurementServiceImpl.getMeasurementsByInterval(
			PersonMeasurementIntervalDto(
				person.id ?: -1,
				startDate,
				endDate,
				listOf(PersonMeasurementType.NONE)
			)
		)

		assertTrue(foundMeasurementNone.isEmpty())
	}
}
