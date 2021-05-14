package cz.upce.nnpia.personstatistics.entity

import cz.upce.nnpia.personstatistics.dto.PersonDto
import cz.upce.nnpia.personstatistics.repository.AbstractJpaPersistable
import javax.persistence.*

@Entity(name = "Person")
@Table(name = "person")
class Person(
	@Column(length = 100, unique = true) var username: String?,
	@Column(length = 500) var password: String?,
	@OneToMany(
		mappedBy = "person",
		cascade = [CascadeType.ALL],
		orphanRemoval = true
	) var personMeasurement: MutableList<PersonMeasurement>
) : AbstractJpaPersistable<Long>() {

	fun toDtoClass(): PersonDto {
		return PersonDto(this.id ?: -1, this.username ?: "", this.password ?: "")
	}
}
