package cz.upce.nnpia.personstatistics.auth

import cz.upce.nnpia.personstatistics.security.ApplicationUserRole.*
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import java.util.*

//TODO This repository will be database
@Repository("fake")
class FakeApplicationUserDaoService(
        private val passwordEncoder: PasswordEncoder
) : ApplicationUserDao {

    override fun selectApplicationUserByUsername(username: String): Optional<ApplicationUser> {
        return getApplicationUsers()
                .stream()
                .filter { applicationUser -> username == applicationUser.username }
                .findFirst()
    }

    fun getApplicationUsers(): List<ApplicationUser> {
        return listOf(
                ApplicationUser(
                        username = "admin",
                        password = passwordEncoder.encode("admin"),
                        grantedAuthority = ADMIN.getGrantedAuthorities(),
                        isAccountNonExpired = true,
                        isAccountNonLocked = true,
                        isEnabled = true,
                        isCredentialsNonExpired = true
                ),
                ApplicationUser(
                        username = "vip",
                        password = passwordEncoder.encode("vip"),
                        grantedAuthority = VIP.getGrantedAuthorities(),
                        isAccountNonExpired = true,
                        isAccountNonLocked = true,
                        isEnabled = true,
                        isCredentialsNonExpired = true
                ),
                ApplicationUser(
                        username = "rampageroman",
                        password = passwordEncoder.encode("rampageroman"),
                        grantedAuthority = USER.getGrantedAuthorities(),
                        isAccountNonExpired = true,
                        isAccountNonLocked = true,
                        isEnabled = true,
                        isCredentialsNonExpired = true
                ),
                ApplicationUser(
                        username = "stoplusto",
                        password = passwordEncoder.encode("stoplusto"),
                        grantedAuthority = USER.getGrantedAuthorities(),
                        isAccountNonExpired = true,
                        isAccountNonLocked = true,
                        isEnabled = true,
                        isCredentialsNonExpired = true
                ),
                ApplicationUser(
                        username = "sufusky",
                        password = passwordEncoder.encode("sufusky"),
                        grantedAuthority = USER.getGrantedAuthorities(),
                        isAccountNonExpired = true,
                        isAccountNonLocked = true,
                        isEnabled = true,
                        isCredentialsNonExpired = true
                ),
                ApplicationUser(
                        username = "sufurky",
                        password = passwordEncoder.encode("sufurky"),
                        grantedAuthority = USER.getGrantedAuthorities(),
                        isAccountNonExpired = true,
                        isAccountNonLocked = true,
                        isEnabled = true,
                        isCredentialsNonExpired = true
                ),
                ApplicationUser(
                        username = "akafuka",
                        password = passwordEncoder.encode("akafuka"),
                        grantedAuthority = USER.getGrantedAuthorities(),
                        isAccountNonExpired = true,
                        isAccountNonLocked = true,
                        isEnabled = true,
                        isCredentialsNonExpired = true
                ),
                ApplicationUser(
                        username = "fundeluka",
                        password = passwordEncoder.encode("fundeluka"),
                        grantedAuthority = USER.getGrantedAuthorities(),
                        isAccountNonExpired = true,
                        isAccountNonLocked = true,
                        isEnabled = true,
                        isCredentialsNonExpired = true
                ),
                ApplicationUser(
                        username = "fundeluka",
                        password = passwordEncoder.encode("fundeluka"),
                        grantedAuthority = USER.getGrantedAuthorities(),
                        isAccountNonExpired = true,
                        isAccountNonLocked = true,
                        isEnabled = true,
                        isCredentialsNonExpired = true
                ),
                ApplicationUser(
                        username = "petpluspet",
                        password = passwordEncoder.encode("petpluspet"),
                        grantedAuthority = USER.getGrantedAuthorities(),
                        isAccountNonExpired = true,
                        isAccountNonLocked = true,
                        isEnabled = true,
                        isCredentialsNonExpired = true
                ),
                ApplicationUser(
                        username = "devat",
                        password = passwordEncoder.encode("devat"),
                        grantedAuthority = USER.getGrantedAuthorities(),
                        isAccountNonExpired = true,
                        isAccountNonLocked = true,
                        isEnabled = true,
                        isCredentialsNonExpired = true
                ),
                ApplicationUser(
                        username = "desat",
                        password = passwordEncoder.encode("desat"),
                        grantedAuthority = USER.getGrantedAuthorities(),
                        isAccountNonExpired = true,
                        isAccountNonLocked = true,
                        isEnabled = true,
                        isCredentialsNonExpired = true
                )
        )
    }
}
