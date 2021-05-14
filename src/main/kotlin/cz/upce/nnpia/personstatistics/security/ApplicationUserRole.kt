package cz.upce.nnpia.personstatistics.security

import com.google.common.collect.Sets
import cz.upce.nnpia.personstatistics.security.ApplicationUserPermission.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors.toSet

enum class ApplicationUserRole(
	private val permission: Set<ApplicationUserPermission>
) {
	ADMIN(Sets.newHashSet(PROFILE_CREATE, PROFILE_DELETE, PROFILE_UPDATE, PROFILE_READ)),
	USER(Sets.newHashSet(PROFILE_CREATE, PROFILE_READ)),
	VIP(Sets.newHashSet(PROFILE_READ));

	fun getGrantedAuthorities(): Set<SimpleGrantedAuthority> {
		val listOfAuthorities = permission.stream()
			.map { permission -> SimpleGrantedAuthority(permission.permission) }
			.collect(toSet())

		listOfAuthorities.add(SimpleGrantedAuthority("ROLE_" + this.name))
		return listOfAuthorities
	}
}
