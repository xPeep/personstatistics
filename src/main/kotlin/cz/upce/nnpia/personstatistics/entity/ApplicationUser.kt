package cz.upce.nnpia.personstatistics.entity

import cz.upce.nnpia.personstatistics.dto.UserDto
import cz.upce.nnpia.personstatistics.repository.AbstractJpaPersistable
import cz.upce.nnpia.personstatistics.security.UserRole
import javax.persistence.*

@Entity(name = "ApplicationUser")
@Table(name = "application_user")
class ApplicationUser(
	@Column(length = 100, unique = true) var username: String?,
	@Column(length = 500) var password: String?,
	@Enumerated(EnumType.STRING) @Column(length = 100) var role: UserRole?
) : AbstractJpaPersistable<Long>() {

	fun toDtoClass(): UserDto {
		return UserDto(this.id ?: -1, this.username ?: "", this.password ?: "")
	}

}
