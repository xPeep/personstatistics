package cz.upce.nnpia.personstatistics.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

//TODO: CustomUserDetails with uid, sid
class ApplicationUser(
        private val grantedAuthority: Set<GrantedAuthority>,
        private val password: String,
        private val username: String,
        private val isAccountNonExpired: Boolean,
        private val isAccountNonLocked: Boolean,
        private val isCredentialsNonExpired: Boolean,
        private val isEnabled: Boolean
) : UserDetails {

    override fun getAuthorities(): Set<GrantedAuthority> {
        return grantedAuthority
    }

    override fun isEnabled(): Boolean {
        return isEnabled
    }

    override fun getUsername(): String {
        return username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return isCredentialsNonExpired
    }

    override fun getPassword(): String {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        return isAccountNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return isAccountNonLocked
    }
}
