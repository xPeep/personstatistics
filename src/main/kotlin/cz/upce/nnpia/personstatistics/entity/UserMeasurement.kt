package cz.upce.nnpia.personstatistics.entity

import cz.upce.nnpia.personstatistics.dto.UserMeasurementDto
import cz.upce.nnpia.personstatistics.repository.AbstractJpaPersistable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class UserMeasurement(
	@Column(columnDefinition = "TIMESTAMP") var timeStamp: LocalDateTime,
	@Column(nullable = true) var weight: Double?,
	@Column(nullable = true) var abdomenSize: Double?,
	@Column(nullable = true) var leftHandSize: Double?,
	@Column(nullable = true) var rightHandSize: Double?,
	@Column(nullable = true) var leftLegSize: Double?,
	@Column(nullable = true) var rightLegSize: Double?,
	@Column(nullable = true) var chestSize: Double?,
	@ManyToOne(fetch = FetchType.LAZY) var applicationUser: ApplicationUser? = null
) : AbstractJpaPersistable<Long>() {

	fun toDtoClass(): UserMeasurementDto {
		return UserMeasurementDto(
			this.id,
			this.timeStamp,
			this.weight,
			this.abdomenSize,
			this.leftHandSize,
			this.rightHandSize,
			this.leftLegSize,
			this.rightLegSize,
			this.chestSize,
			this.applicationUser?.id
		)
	}
}
