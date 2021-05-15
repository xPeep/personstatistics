package cz.upce.nnpia.personstatistics.jwt

import com.google.common.net.HttpHeaders
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "application.jwt")
data class JwtConfig(
	var secretKey: String = "",
	var tokenPrefix: String = "",
	var tokenExpirationAfterDays: Long?,
	var authorities: String = ""
) {

	fun getAuthorizationHeader(): String {
		return HttpHeaders.AUTHORIZATION
	}

}
