package cz.upce.nnpia.personstatistics

import cz.upce.eshop.ui.TestImplementation
import cz.upce.nnpia.personstatistics.repository.ApplicationUserRepository
import org.junit.Assert
import org.junit.jupiter.api.*
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles
import java.io.File


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@ComponentScan
@ExperimentalStdlibApi
class LogRegSeleniumTest
@Autowired constructor(
	private val applicationUserRepository: ApplicationUserRepository,
	private val personalMockGenerator: PersonMockGenerator,
	private val creator: Creator
) {

	private lateinit var driver: WebDriver
	private lateinit var wait: WebDriverWait

	@BeforeAll
	fun setupWebdriverChromeDriver() {
		val chromeDriverPath = TestImplementation::class.java.getResource("/chromedriver.exe").file
		val circleCIChromedriverPath = "/usr/local/bin/chromedriver"

		if (File(circleCIChromedriverPath).exists()) {
			System.setProperty(
				"webdriver.chrome.driver",
				circleCIChromedriverPath
			)
		} else {
			System.setProperty(
				"webdriver.chrome.driver",
				chromeDriverPath
			)
		}
	}

	@BeforeEach
	fun setup() {
		val chromeOptions = ChromeOptions()
		chromeOptions.setHeadless(true)
		driver = ChromeDriver(chromeOptions)
		applicationUserRepository.deleteAll()
		wait = WebDriverWait(driver, 10)
	}

	@AfterEach
	fun teardown() {
		if (driver != null) {
			driver?.quit()
		}
	}

	fun getOrigin(): String {
		return "https://personal-statistics.herokuapp.com"
	}

	@Test
	fun changeName() {
		val user = personalMockGenerator.createPersonal()
		creator.save(user.toEntityClass())

		driver?.get(getOrigin())
		driver?.findElement(By.id("userName"))?.sendKeys("pepe95")
		driver?.findElement(By.id("password"))?.sendKeys("lolko")
		driver?.findElement(By.xpath("//button[@id='buttonSubmit']"))?.click()

		wait.until(ExpectedConditions.urlContains(getOrigin() + "/home"))

		driver?.findElement(By.xpath("//a[@href='/profile']")).click()

		wait.until(ExpectedConditions.urlContains(getOrigin() + "/profile"))

		for (x in 0..5) {
			driver?.findElement(By.id("firstName")).sendKeys(Keys.BACK_SPACE);
		}

		driver?.findElement(By.id("firstName")).sendKeys("Karel")

		driver?.findElement(By.xpath("//button[@id='saveProfileDataButton']"))?.click()

		Assert.assertEquals(
			"Karel",
			driver?.findElement(By.id("firstName")).getAttribute("value")
		)
	}


	@Test
	fun loginSuccess() {
		val user = personalMockGenerator.createPersonal()
		creator.save(user.toEntityClass())

		driver?.get(getOrigin())
		driver?.findElement(By.id("userName"))?.sendKeys("pepe95")
		driver?.findElement(By.id("password"))?.sendKeys("lolko")
		driver?.findElement(By.xpath("//button[@id='buttonSubmit']"))?.click()

		wait.until(ExpectedConditions.urlContains(getOrigin() + "/home"))

		Assert.assertEquals(
			1,
			driver?.findElements(By.xpath("//h1[text()='Welcome to measurement statistics react application !']"))?.size
		)
	}

	@Test
	fun loginFailed() {
		val user = personalMockGenerator.createPersonal()
		creator.save(user.toEntityClass())

		driver?.get(getOrigin())
		driver?.findElement(By.id("userName"))?.sendKeys("admin")
		driver?.findElement(By.id("password"))?.sendKeys("0000")
		driver?.findElement(By.xpath("//button[@id='buttonSubmit']"))?.click()

		Assert.assertEquals(1, driver?.findElements(By.xpath("//button[@id='buttonSubmit']"))?.size)
	}
}
