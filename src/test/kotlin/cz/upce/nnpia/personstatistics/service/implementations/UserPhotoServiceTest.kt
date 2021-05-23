package cz.upce.nnpia.personstatistics.service.implementations

import cz.upce.nnpia.personstatistics.PersonMockGenerator
import cz.upce.nnpia.personstatistics.repository.ApplicationUserRepository
import cz.upce.nnpia.personstatistics.repository.UserMeasurementRepository
import cz.upce.nnpia.personstatistics.repository.UserPhotoRepository
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
class UserPhotoServiceTest
@Autowired constructor(
	private val personalMockGenerator: PersonMockGenerator,
	private val applicationUserRepository: ApplicationUserRepository,
	private val userPhotoService: UserPhotoService,
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
	fun add() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		applicationUserRepository.save(person)
		val userMedia = personalMockGenerator.createUserMedia(person.id ?: -1)
		userMedia.userId = person.id
		userPhotoService.add(userMedia)
		val foundUserPhoto = userPhotoRepository.findAll().firstOrNull()?.toDtoClass()
		assertThat(foundUserPhoto?.imagePath).isEqualTo(userMedia.imagePath)
	}

	@Test
	fun removeById() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		applicationUserRepository.save(person)
		val userMedia = personalMockGenerator.createUserMedia(person.id ?: -1)
		userMedia.userId = person.id
		userPhotoService.add(userMedia)
		val foundUserPhoto = userPhotoRepository.findAll().firstOrNull()?.toDtoClass()
		userPhotoService.removeById(foundUserPhoto?.id ?: -1)
		assertTrue(userPhotoRepository.findAll().isEmpty())
	}

	@Test
	fun getAll() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		applicationUserRepository.save(person)

		val userPhotos = listOf(
			personalMockGenerator.createUserMedia(person.id ?: -1),
			personalMockGenerator.createUserMedia(person.id ?: -1),
			personalMockGenerator.createUserMedia(person.id ?: -1),
			personalMockGenerator.createUserMedia(person.id ?: -1)
		)

		userPhotos.forEach { userPhotoUnit ->
			userPhotoUnit.userId = person.id
			userPhotoService.add(userPhotoUnit)
		}

		val foundUserPhoto = userPhotoService.getAll(person.id ?: -1)
		assertThat(userPhotos.map { it.imagePath }).containsAll(foundUserPhoto.map { it.imagePath })
	}
}
