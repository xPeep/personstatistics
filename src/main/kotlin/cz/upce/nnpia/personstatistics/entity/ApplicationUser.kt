package cz.upce.nnpia.personstatistics.entity

import cz.upce.nnpia.personstatistics.dto.UserDto
import cz.upce.nnpia.personstatistics.repository.AbstractJpaPersistable
import cz.upce.nnpia.personstatistics.security.UserRole
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
class ApplicationUser(
	@Column(length = 100, unique = true) var nickname: String,
	@Column(length = 500) var userPassword: String,
	@Enumerated(EnumType.STRING) @Column(length = 100) var role: UserRole,
	@Column(length = 100) var firstName: String,
	@Column(length = 100) var lastName: String,
	@Column(length = 500, unique = true) var emailAddress: String,
	@OneToMany(
		mappedBy = "id",
		cascade = [CascadeType.ALL],
		orphanRemoval = true
	) var userMeasurement: MutableList<UserMeasurement>,
	@OneToMany(
		mappedBy = "id",
		cascade = [CascadeType.ALL],
		orphanRemoval = true
	) var userPhotos: MutableList<UserPhoto>,
) : AbstractJpaPersistable<Long>(), UserDetails {

	fun toDtoClass(): UserDto {
		return UserDto(
			this.id,
			this.nickname,
			this.userPassword,
			this.firstName,
			this.lastName,
			this.emailAddress,
			this.role,
			this.userPhotos,
			this.userMeasurement
		)
	}

	override fun getAuthorities() = role.getGrantedAuthorities()

	override fun getPassword() = userPassword

	override fun getUsername() = nickname

	override fun isAccountNonExpired() = true

	override fun isAccountNonLocked() = true

	override fun isCredentialsNonExpired() = true

	override fun isEnabled() = true
}
