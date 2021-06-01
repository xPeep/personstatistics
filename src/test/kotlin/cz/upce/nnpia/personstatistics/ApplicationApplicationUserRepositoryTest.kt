package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.entity.ApplicationUser
import cz.upce.nnpia.personstatistics.repository.ApplicationUserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@ComponentScan
@ExperimentalStdlibApi
class ApplicationApplicationUserRepositoryTest
@Autowired constructor(
	private val creator: Creator,
	private val applicationUserRepository: ApplicationUserRepository,
	private val personalMockGenerator: PersonMockGenerator
) {

	@BeforeEach
	fun clean() {
		applicationUserRepository.deleteAll()
	}

	@Test
	fun `should not find any user`() {
		assertThat(applicationUserRepository.findAll()).isEmpty()
	}

	@Test
	fun `should store new user`() {
		val newUser = personalMockGenerator.createPersonal().toEntityClass()
		val foundUser = creator.save(newUser)

		assertThat(foundUser).isEqualTo(newUser)
	}

	@Test
	fun `should find all users`() {
		val fakeUsers = mutableListOf<ApplicationUser>()

		for (i in 0..10) {
			val user = personalMockGenerator.createPersonal().toEntityClass()
			fakeUsers.add(user)
			creator.save(user)
		}

		assertThat(applicationUserRepository.findAll())
			.hasSize(fakeUsers.size)
			.containsAll(fakeUsers)
	}

	@Test
	fun `should find user by id`() {
		val firstUser = personalMockGenerator.createPersonal().toEntityClass()
		val secondUser = personalMockGenerator.createPersonal().toEntityClass()

		creator.save(firstUser)
		creator.save(secondUser)

		val foundUser = applicationUserRepository.findByIdOrNull(secondUser.id)

		assertThat(foundUser).isEqualTo(secondUser)
	}

	@Test
	fun `should delete all users`() {
		val vipUser = personalMockGenerator.createPersonal().toEntityClass()
		val regularUser = personalMockGenerator.createPersonal().toEntityClass()
		val adminUser = personalMockGenerator.createPersonal().toEntityClass()

		creator.save(vipUser)
		creator.save(regularUser)
		creator.save(adminUser)

		applicationUserRepository.deleteAll()

		assertThat(applicationUserRepository.findAll()).isEmpty()
	}

}
