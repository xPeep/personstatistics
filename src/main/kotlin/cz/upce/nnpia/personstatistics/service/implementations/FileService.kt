package cz.upce.nnpia.personstatistics.service.implementations

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class FileService {

	fun upload(file: MultipartFile): String {
		try {
			val path =
				Paths.get("src/test/kotlin/cz/upce/nnpia/personstatistics/service/implementations/images/" + file.originalFilename)
			Files.copy(file.inputStream, path, StandardCopyOption.REPLACE_EXISTING)
			return file.originalFilename ?: throw IllegalStateException("File is not exist")
		} catch (e: IOException) {
			throw IOException("File is not possible save" + e.printStackTrace())
		}
	}

}
