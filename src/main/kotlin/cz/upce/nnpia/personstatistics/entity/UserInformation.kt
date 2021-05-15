package cz.upce.nnpia.personstatistics.entity

import cz.upce.nnpia.personstatistics.dto.UserInformationDto
import cz.upce.nnpia.personstatistics.repository.AbstractJpaPersistable
import javax.persistence.*

@Entity(name = "UserInformation")
@Table(name = "user_information")
class UserInformation(
	@Column(length = 100) var firstName: String?,
	@Column(length = 100) var lastName: String?,
	@Column(length = 500, unique = true) var emailAddress: String?,
	@OneToMany(
		mappedBy = "id",
		cascade = [CascadeType.ALL],
		orphanRemoval = true
	) var userMeasurement: MutableList<UserMeasurement>,
	@OneToOne(fetch = FetchType.LAZY) @MapsId var applicationUser: ApplicationUser? = null
) : AbstractJpaPersistable<Long>() {

	fun toDtoClass(): UserInformationDto {
		return UserInformationDto(
			this.id ?: -1,
			this.firstName ?: "",
			this.firstName ?: "",
			this.emailAddress ?: "",
			userMeasurement
		)
	}
}
