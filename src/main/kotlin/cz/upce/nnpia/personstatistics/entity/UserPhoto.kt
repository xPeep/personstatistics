package cz.upce.nnpia.personstatistics.entity

import cz.upce.nnpia.personstatistics.dto.UserPhotoDto
import cz.upce.nnpia.personstatistics.repository.AbstractJpaPersistable
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class UserPhoto(
	@Column var imagePath: String?,
	@Column(columnDefinition = "TIMESTAMP") var timeStamp: LocalDateTime,
	@ManyToOne(fetch = FetchType.LAZY) var applicationUser: ApplicationUser? = null
) : AbstractJpaPersistable<Long>() {

	fun toDtoClass(): UserPhotoDto {
		return UserPhotoDto(
			this.id,
			this.timeStamp,
			this.imagePath,
			this.applicationUser?.id
		)
	}
}
