package cz.upce.nnpia.personstatistics.entity

import cz.upce.nnpia.personstatistics.dto.UserMeasurementDto
import cz.upce.nnpia.personstatistics.repository.AbstractJpaPersistable
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "UserMeasurement")
@Table(name = "user_measurement")
class UserMeasurement(
	@Column(length = 500) var timeStamp: LocalDateTime?,
	@Column(length = 500) var value: Double?,
	@Enumerated(EnumType.STRING) @Column(length = 50) var type: UserMeasurementType?,
	@ManyToOne(fetch = FetchType.LAZY) var userInformation: UserInformation? = null
) : AbstractJpaPersistable<Long>() {

	fun toDtoClass(): UserMeasurementDto {
		return UserMeasurementDto(
			this.id ?: -1,
			this.value ?: 0.0,
			this.timeStamp ?: LocalDateTime.now(),
			this.type ?: UserMeasurementType.NONE
		)
	}
}
