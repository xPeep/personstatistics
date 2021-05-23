package cz.upce.nnpia.personstatistics.service.implementations

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths

@SpringBootTest
@ActiveProfiles("test")
@ComponentScan
class FileServiceTest
@Autowired constructor(
	private val fileService: FileService
) {

	@Test
	fun upload() {
		val path = Paths.get("src/test/kotlin/cz/upce/nnpia/personstatistics/service/implementations/images/img.png")

		val result: MultipartFile = MockMultipartFile(
			"img", "img.png", "image/png", Files.readAllBytes(path)
		)

		val filePath = fileService.upload(result)
		assertThat(filePath == result.originalFilename)
	}
}
