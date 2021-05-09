package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.entity.PersonMeasurementType
import cz.upce.nnpia.personstatistics.repository.PersonMeasurementRepository
import cz.upce.nnpia.personstatistics.repository.PersonRepository
import cz.upce.nnpia.personstatistics.service.implementations.PersonMeasurementServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan

@DataJpaTest
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
		personMeasurementServiceImpl.addMeasurement(person.id ?: -1, personMeasurement)
		val foundMeasurement = personMeasurementRepository.findAll().firstOrNull()?.toDtoClass()
		assertThat(personMeasurement.timestamp).isEqualTo(foundMeasurement?.timestamp)
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
			personMeasurementServiceImpl.addMeasurement(person.id ?: -1, personMeasurement)
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
			personMeasurementServiceImpl.addMeasurement(person.id ?: -1, personMeasurement)
		}

		val foundMeasurement = personMeasurementServiceImpl.getAllMeasurements(person.id ?: -1)

		assertTrue(personMeasurements.map { it.timestamp }.containsAll(foundMeasurement.map { it.timestamp }))
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
			personMeasurementServiceImpl.addMeasurement(person.id ?: -1, personMeasurement)
		}

		val foundMeasurement = personMeasurementServiceImpl.getMeasurementsByInterval(
			person.id ?: -1,
			startDate,
			endDate,
			PersonMeasurementType.values().toList()
		)

		assertTrue(testedPersonMeasurements.map { it.timestamp }.containsAll(foundMeasurement.map { it.timestamp }))

		val foundMeasurementNone = personMeasurementServiceImpl.getMeasurementsByInterval(
			person.id ?: -1,
			startDate,
			endDate,
			listOf(PersonMeasurementType.NONE)
		)

		assertTrue(foundMeasurementNone.isEmpty())
	}
}
