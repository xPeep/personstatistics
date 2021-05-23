package cz.upce.nnpia.personstatistics.controller

import cz.upce.nnpia.personstatistics.dto.UserPhotoDto
import cz.upce.nnpia.personstatistics.service.implementations.FileService
import cz.upce.nnpia.personstatistics.service.implementations.UserPhotoService
import cz.upce.nnpia.personstatistics.service.implementations.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@RestController
@RequestMapping("api/media")
@CrossOrigin("*")
class UserPhotoController
@Autowired constructor(
	private val userPhotoService: UserPhotoService,
	private val fileService: FileService,
	private val userService: UserService
) {

	@ExceptionHandler(RuntimeException::class)
	fun handleException(): String = "error"

	@PostMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	fun add(@RequestBody multipartFile: MultipartFile) {
		val path = fileService.upload(multipartFile)
		userPhotoService.add(UserPhotoDto(null, LocalDateTime.now(), path, userService.getLoggedUserId()))
	}

	@DeleteMapping("/{measurementId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	fun remove(@PathVariable measurementId: Long) {
		userPhotoService.removeById(measurementId)
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	fun all(): List<UserPhotoDto> {
		return userPhotoService.getAll(userService.getLoggedUserId())
	}

}
