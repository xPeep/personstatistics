package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.entity.Person
import cz.upce.nnpia.personstatistics.repository.PersonRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
@ComponentScan
@ExperimentalStdlibApi
class PersonRepositoryTest
@Autowired constructor(
	private val creator: Creator,
	private val personRepository: PersonRepository,
	private val personalMockGenerator: PersonMockGenerator
) {

	@Test
	fun `should not find any user`() {
		assertThat(personRepository.findAll()).isEmpty()
	}

	@Test
	fun `should store new user`() {
		val newUser = personalMockGenerator.createPersonal()
		val foundUser = creator.save(newUser)

		assertThat(foundUser).isEqualTo(newUser)
	}

	@Test
	fun `should find all users`() {
		val fakeUsers = mutableListOf<Person>()

		for (i in 0..10) {
			val user = personalMockGenerator.createPersonal()
			fakeUsers.add(user)
			creator.save(user)
		}

		assertThat(personRepository.findAll())
			.hasSize(fakeUsers.size)
			.containsAll(fakeUsers)
	}

	@Test
	fun `should find user by id`() {
		val firstUser = personalMockGenerator.createPersonal()
		val secondUser = personalMockGenerator.createPersonal()

		creator.save(firstUser)
		creator.save(secondUser)

		val foundUser = personRepository.findByIdOrNull(secondUser.id)

		assertThat(foundUser).isEqualTo(secondUser)
	}

	@Test
	fun `should delete all users`() {
		val vipUser = personalMockGenerator.createPersonal()
		val regularUser = personalMockGenerator.createPersonal()
		val adminUser = personalMockGenerator.createPersonal()

		creator.save(vipUser)
		creator.save(regularUser)
		creator.save(adminUser)

		personRepository.deleteAll()

		assertThat(personRepository.findAll()).isEmpty()
	}

}
