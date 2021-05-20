package cz.upce.nnpia.personstatistics.repository

import cz.upce.nnpia.personstatistics.entity.ApplicationUser
import org.springframework.data.jpa.repository.JpaRepository

interface ApplicationUserRepository : JpaRepository<ApplicationUser, Long> {
	fun findByNickname(nickname: String): ApplicationUser?
}
