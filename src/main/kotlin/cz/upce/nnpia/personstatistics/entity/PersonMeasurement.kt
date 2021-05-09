package cz.upce.nnpia.personstatistics.entity

import cz.upce.nnpia.personstatistics.repository.AbstractJpaPersistable
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "PERSON_MEASUREMENT")
class PersonMeasurement(
	@Column(length = 500) var timeStamp: LocalDateTime = LocalDateTime.now(),
	@Column(length = 500) var value: Double = 0.0,
	@Enumerated(EnumType.STRING) @Column(length = 50) var type: PersonMeasurementType = PersonMeasurementType.NONE,
	@ManyToOne(fetch= FetchType.LAZY) var person: Person? = null
) : AbstractJpaPersistable<Long>()
