package cz.upce.nnpia.personstatistics.auth

import org.springframework.security.core.Authentication


interface IAuthenticationFacade {
	fun provideAuthentication(): Authentication?
	fun getUserName(): String
}
