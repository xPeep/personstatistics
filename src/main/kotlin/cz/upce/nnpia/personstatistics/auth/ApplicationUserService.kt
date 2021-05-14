package cz.upce.nnpia.personstatistics.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class ApplicationUserService(
        @Autowired
        @Qualifier("fake")
        private val applicationUserDao: ApplicationUserDao
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return applicationUserDao.selectApplicationUserByUsername(username)
                .orElseThrow()
    }
}
