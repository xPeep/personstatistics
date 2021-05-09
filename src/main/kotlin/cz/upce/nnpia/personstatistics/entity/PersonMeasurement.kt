package cz.upce.nnpia.personstatistics.entity

import cz.upce.nnpia.personstatistics.dto.PersonMeasurementDto
import cz.upce.nnpia.personstatistics.repository.AbstractJpaPersistable
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "PersonMeasurement")
@Table(name = "person_measurement")
class PersonMeasurement(
	@Column(length = 500) var timeStamp: LocalDateTime?,
	@Column(length = 500) var value: Double?,
	@Enumerated(EnumType.STRING) @Column(length = 50) var type: PersonMeasurementType?,
	@ManyToOne(fetch = FetchType.LAZY) var person: Person? = null
) : AbstractJpaPersistable<Long>() {

	fun toDtoClass(): PersonMeasurementDto {
		return PersonMeasurementDto(
			this.id ?: -1,
			this.value ?: 0.0,
			this.timeStamp ?: LocalDateTime.now(),
			this.type ?: PersonMeasurementType.NONE
		)
	}
}
