package cz.upce.nnpia.personstatistics.repository

import cz.upce.nnpia.personstatistics.entity.ApplicationUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ApplicationUserRepository : JpaRepository<ApplicationUser, Long> {
	fun findByNickname(nickname: String): ApplicationUser?
	fun findByEmailAddress(nickname: String): ApplicationUser?

	@Query("UPDATE ApplicationUser u set u.nickname=:username, u.userPassword=:password, u.emailAddress=:email, u.firstName=:firstName, u.lastName=:lastName where u.id=:id")
	fun updateUser(id: Long, username: String, password: String, email: String, firstName: String, lastName: String)
}
