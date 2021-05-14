package cz.upce.nnpia.personstatistics.jwt

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.IOException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.time.LocalDate
import java.util.*
import javax.crypto.SecretKey
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtUsernameAndPasswordAuthenticationFilter(
	private val authenticationManagerLocal: AuthenticationManager,
	private val jwtConfig: JwtConfig,
	private val secretKey: SecretKey
) : UsernamePasswordAuthenticationFilter() {

    //TODO Renew token

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {

        try {
            val authenticationRequest = jacksonObjectMapper().readValue<UsernameAndPasswordAuthenticationRequest>(request.inputStream)

            return authenticationManagerLocal.authenticate(
                    UsernamePasswordAuthenticationToken(
                            authenticationRequest.username,
                            authenticationRequest.password
                    )
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

    }

    override fun successfulAuthentication(
            request: HttpServletRequest,
            response: HttpServletResponse,
            chain: FilterChain,
            authResult: Authentication
    ) {
        response.addHeader(jwtConfig.getAuthorizationHeader(),
                jwtConfig.tokenPrefix + generateToken(authResult))
    }


    private fun generateToken(authResult: Authentication): String {
        return Jwts.builder()
                .setSubject(authResult.name)
                .claim(jwtConfig.authorities, authResult.authorities)
                .setIssuedAt(Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(jwtConfig.tokenExpirationAfterDays)))
                .signWith(secretKey)
                .compact()
    }

}
