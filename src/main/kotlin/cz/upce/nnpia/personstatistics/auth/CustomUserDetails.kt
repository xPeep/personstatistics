package cz.upce.nnpia.personstatistics.auth

import cz.upce.nnpia.personstatistics.entity.ApplicationUser
import org.springframework.security.core.userdetails.UserDetails


class CustomUserDetails(
	private val applicationUser: ApplicationUser
) : UserDetails {

	override fun getAuthorities() = applicationUser.role?.getGrantedAuthorities()

	override fun getUsername() = applicationUser.username ?: ""

	override fun getPassword() = applicationUser.password ?: ""

	override fun isEnabled() = true

	override fun isCredentialsNonExpired() = true

	override fun isAccountNonExpired() = true

	override fun isAccountNonLocked() = true

}
