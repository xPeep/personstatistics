package cz.upce.nnpia.personstatistics.entity

import cz.upce.nnpia.personstatistics.repository.AbstractJpaPersistable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "PERSON_INFORMATION")
class PersonInformation(
	@Column(length = 500)
	var firstName: String = "",

	@Column(length = 500)
	var lastName: String = "",

	@Column(length = 500)
	var emailAddress: String = ""
) : AbstractJpaPersistable<Long>()
