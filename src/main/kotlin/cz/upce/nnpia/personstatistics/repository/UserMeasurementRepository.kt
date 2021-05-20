package cz.upce.nnpia.personstatistics.repository


import cz.upce.nnpia.personstatistics.entity.UserMeasurement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface UserMeasurementRepository : JpaRepository<UserMeasurement, Long> {

	@Query("SELECT u from UserMeasurement u where u.applicationUser.id=:id")
	fun findByApplicationUserId(id: Long): List<UserMeasurement>

	@Query("SELECT u from UserMeasurement u where u.applicationUser.id =:userId and u.timeStamp between :start and :end")
	fun findByApplicationUserAndInterval(userId: Long?, start: LocalDateTime?, end: LocalDateTime?): List<UserMeasurement>
}
