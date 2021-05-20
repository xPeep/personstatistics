package cz.upce.nnpia.personstatistics.dto

import org.springframework.web.multipart.MultipartFile

data class UserMediaDto(
	val userId: Long? = null,
	val file: MultipartFile? = null
)
