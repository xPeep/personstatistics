package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.repository.ApplicationUserRepository
import cz.upce.nnpia.personstatistics.repository.UserMeasurementRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest(classes = [UserMeasurementRepository::class])
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@ComponentScan
class MockMvcTestLoginResult
@Autowired constructor(
	private val personalMockGenerator: PersonMockGenerator,
	private val userMeasurementRepository: UserMeasurementRepository,
	private val applicationUserRepository: ApplicationUserRepository,
	private val mockMvc: MockMvc
) {

	@Test
	@Throws(Exception::class)
	fun `measurements get all`() {
		val person = personalMockGenerator.createPersonal().toEntityClass()
		applicationUserRepository.save(person)
		val personMeasurement = personalMockGenerator.createPersonalMeasurement()
		personMeasurement.userId = person.id ?: -1
		userMeasurementRepository.save(personMeasurement.toEntityClass(person))

		this.mockMvc.perform(
			get("/api/measurement")
				.accept(MediaType.APPLICATION_JSON)
		)
			.andExpect(status().isOk)
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	}

	@Test
	@Throws(Exception::class)
	fun `users get all`() {
		val user = personalMockGenerator.createPersonal().toEntityClass()
		applicationUserRepository.save(user)

		this.mockMvc.perform(
			get("/api/user/{userId}", user.id)
				.accept(MediaType.APPLICATION_JSON)
		)
			.andExpect(status().isOk)
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	}

}
