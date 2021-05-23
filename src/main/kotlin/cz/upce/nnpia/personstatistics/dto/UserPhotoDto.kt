package cz.upce.nnpia.personstatistics.dto

import cz.upce.nnpia.personstatistics.entity.ApplicationUser
import cz.upce.nnpia.personstatistics.entity.UserPhoto
import java.time.LocalDateTime

data class UserPhotoDto(
	val id: Long? = null,
	val timestamp: LocalDateTime,
	var imagePath: String? = null,
	var userId: Long? = null
) {

	fun toEntityClass(applicationUser: ApplicationUser): UserPhoto {
		val userPhoto = UserPhoto(
			this.imagePath,
			this.timestamp,
			applicationUser
		)
		userPhoto.id = this.id
		return userPhoto
	}

}
