package cz.upce.nnpia.personstatistics.entity

import cz.upce.nnpia.personstatistics.dto.PersonInformationDto
import cz.upce.nnpia.personstatistics.repository.AbstractJpaPersistable
import javax.persistence.*

@Entity(name = "PersonInformation")
@Table(name = "person_information")
class PersonInformation(
	@Column(length = 100) var firstName: String?,
	@Column(length = 100) var lastName: String?,
	@Column(length = 500, unique=true) var emailAddress: String?,
	@OneToOne(fetch = FetchType.LAZY) @MapsId var person: Person? = null
) : AbstractJpaPersistable<Long>() {

	fun toDtoClass(): PersonInformationDto {
		return PersonInformationDto(
			this.id ?: -1,
			this.firstName ?: "",
			this.firstName ?: "",
			this.emailAddress ?: ""
		)
	}
}
