package cz.upce.nnpia.personstatistics.entity

import cz.upce.nnpia.personstatistics.repository.AbstractJpaPersistable
import javax.persistence.*

@Entity(name = "PERSON")
class Person(
	@Column(length = 100) var username: String = "",
	@Column(length = 500) var password: String = "",
	@OneToOne(cascade = [CascadeType.ALL]) var personInformation: PersonInformation? = null,
	@OneToMany(
		mappedBy = "id",
		cascade = [CascadeType.ALL],
		fetch = FetchType.LAZY
	) var personMeasurement: MutableList<PersonMeasurement> = mutableListOf()
) : AbstractJpaPersistable<Long>()
