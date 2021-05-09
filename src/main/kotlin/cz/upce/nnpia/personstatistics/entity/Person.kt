package cz.upce.nnpia.personstatistics.entity

import cz.upce.nnpia.personstatistics.repository.AbstractJpaPersistable
import javax.persistence.*

@Entity(name = "PERSON")
class Person(
	@Column(length = 100) var username: String = "",
	@Column(length = 500) var password: String = "",
	@OneToMany(
		mappedBy = "id",
		cascade = [CascadeType.ALL],
		fetch = FetchType.LAZY
	) var personMeasurement: MutableList<PersonMeasurement> = mutableListOf()
) : AbstractJpaPersistable<Long>()
