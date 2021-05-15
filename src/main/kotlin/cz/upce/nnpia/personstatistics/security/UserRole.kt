package cz.upce.nnpia.personstatistics.security

import com.google.common.collect.Sets
import cz.upce.nnpia.personstatistics.security.UserPermission.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors.toSet


enum class UserRole {
	ADMIN, USER;

	fun getGrantedAuthorities(): Set<SimpleGrantedAuthority> {

		val permission = Sets.newHashSet<UserPermission>()

		when (valueOf(this.name)) {
			ADMIN -> permission.addAll(listOf(PROFILE_CREATE, PROFILE_DELETE, PROFILE_UPDATE, PROFILE_READ))
			USER -> permission.addAll(listOf(PROFILE_CREATE, PROFILE_READ))
		}

		val listOfAuthorities = permission.stream()
			.map { SimpleGrantedAuthority(it.permission) }
			.collect(toSet())

		listOfAuthorities.add(SimpleGrantedAuthority("ROLE_" + this.name))
		return listOfAuthorities
	}
}
