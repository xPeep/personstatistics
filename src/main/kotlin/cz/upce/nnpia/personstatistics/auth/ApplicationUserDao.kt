package cz.upce.nnpia.personstatistics.auth
import java.util.*

interface ApplicationUserDao {
    fun selectApplicationUserByUsername(username: String): Optional<ApplicationUser>
}
