package cz.upce.nnpia.personstatistics.security

import cz.upce.nnpia.personstatistics.jwt.JwtConfig
import cz.upce.nnpia.personstatistics.jwt.JwtTokenVerifier
import cz.upce.nnpia.personstatistics.jwt.JwtUsernameAndPasswordAuthenticationFilter
import cz.upce.nnpia.personstatistics.service.implementations.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import javax.crypto.SecretKey

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class ApplicationSecurityConfig(
	val passwordEncoder: PasswordEncoder,
	val userService: UserService,
	val secretKey: SecretKey,
	val jwtConfig: JwtConfig
) : WebSecurityConfigurerAdapter() {

	override fun configure(http: HttpSecurity) {
		http
			//.cors().and()
			.csrf().disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilter(JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
			.addFilterAfter(
				JwtTokenVerifier(secretKey, jwtConfig),
				JwtUsernameAndPasswordAuthenticationFilter::class.java
			)
			.authorizeRequests()
			.antMatchers("/", "index", "/css/*", "/js/*").permitAll()
			//.antMatchers("/api/**").hasRole(ApplicationUserRole.USER.name)
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
	}

	@Throws(Exception::class)
	override fun configure(auth: AuthenticationManagerBuilder) {
		auth.authenticationProvider(daoAuthenticationProvider())
	}

	@Bean
	fun corsConfigurationSource(): CorsConfigurationSource {
		val source = UrlBasedCorsConfigurationSource()
		val config = CorsConfiguration()
		config.applyPermitDefaultValues()
		config.addAllowedMethod("DELETE")
		source.registerCorsConfiguration("/**", config)
		return source
	}

	@Bean
	fun daoAuthenticationProvider(): DaoAuthenticationProvider {
		val provider = DaoAuthenticationProvider()
		provider.setPasswordEncoder(passwordEncoder)
		provider.setUserDetailsService(userService)
		return provider
	}
}
