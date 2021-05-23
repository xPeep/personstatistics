package cz.upce.nnpia.personstatistics.repository

import cz.upce.nnpia.personstatistics.entity.UserPhoto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserPhotoRepository : JpaRepository<UserPhoto, Long> {

	@Query("SELECT u from UserPhoto u where u.applicationUser.id=:userId")
	fun findByApplicationUserId(userId: Long): List<UserPhoto>

}
