package cz.upce.nnpia.personstatistics.entity

import cz.upce.nnpia.personstatistics.repository.AbstractJpaPersistable
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "PERSON_MEASUREMENT")
class PersonMeasurement(
	@Column(length = 500) var timeStamp: LocalDateTime?,
	@Column(length = 500) var value: Double?,
	@Enumerated(EnumType.STRING) @Column(length = 50) var type: PersonMeasurementType?,
	@ManyToOne(fetch= FetchType.LAZY) var person: Person?
) : AbstractJpaPersistable<Long>()
