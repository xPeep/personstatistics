package cz.upce.nnpia.personstatistics.jwt

import com.google.common.base.Strings
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.util.stream.Collectors
import javax.crypto.SecretKey
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenVerifier(
        private val secretKey: SecretKey,
        private val jwtConfig: JwtConfig
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader())

        if (!Strings.isNullOrEmpty(authorizationHeader) && authorizationHeader.startsWith(jwtConfig.tokenPrefix)) {

            val token = authorizationHeader.replace(jwtConfig.tokenPrefix, "")

            try {

                val claimsJws = Jwts.parserBuilder()
                        .setSigningKey(secretKey).build()
                        .parseClaimsJws(token)

                val body = claimsJws.body

                val username = body.subject

                val authorities = body["authorities"] as List<Map<String, String>>

                val simpleGrantedAuthority = authorities
                        .stream()
                        .map { item -> SimpleGrantedAuthority(item["authority"]) }
                        .collect(Collectors.toSet())

                val authentication = UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        simpleGrantedAuthority
                )

                SecurityContextHolder.getContext().authentication = authentication

            } catch (e: JwtException) {
                throw IllegalStateException(String.format("Token %s cannot be trusted", token))
            }
        }
        filterChain.doFilter(request, response)
    }
}
