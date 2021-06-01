package cz.upce.nnpia.personstatistics

import cz.upce.nnpia.personstatistics.service.implementations.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@ComponentScan
class MockMvcTestLoginResult
@Autowired constructor(
	private val personalMockGenerator: PersonMockGenerator,
	private val userService: UserService,
	private val mockMvc: MockMvc
) {

	@Test
	@Throws(Exception::class)
	fun `have to back 200 status due to jwt auth`() {
		this.mockMvc.perform(get("/api/user/all")).andExpect(status().isOk)
	}

	@Test
	@Throws(Exception::class)
	fun `have to back 200 status`() {
		val user = userService.addUser(personalMockGenerator.createPersonal())
		this.mockMvc.perform(get("/api/user/" + user.id)).andExpect(status().isOk)
	}

}
