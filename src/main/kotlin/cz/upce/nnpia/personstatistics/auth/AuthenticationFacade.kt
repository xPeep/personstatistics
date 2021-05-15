package cz.upce.nnpia.personstatistics.auth

import org.springframework.context.annotation.Primary
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Primary
@Component
class AuthenticationFacade : IAuthenticationFacade {

	override fun provideAuthentication(): Authentication {
		return SecurityContextHolder.getContext().authentication
	}

	override fun getUserName(): String {
		return provideAuthentication().name ?: throw IllegalStateException("No user logged in!")
	}

}
