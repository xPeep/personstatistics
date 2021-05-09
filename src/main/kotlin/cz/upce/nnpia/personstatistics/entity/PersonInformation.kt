package cz.upce.nnpia.personstatistics.entity

import cz.upce.nnpia.personstatistics.repository.AbstractJpaPersistable
import javax.persistence.*

@Entity(name = "PERSON_INFORMATION")
class PersonInformation(
	@Column(length = 100) var firstName: String = "",
	@Column(length = 100) var lastName: String = "",
	@Column(length = 500) var emailAddress: String = "",
	@OneToOne(fetch = FetchType.LAZY) @MapsId var person: Person? = null
) : AbstractJpaPersistable<Long>()
