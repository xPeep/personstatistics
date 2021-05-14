package cz.upce.nnpia.personstatistics.jwt

data class UsernameAndPasswordAuthenticationRequest(
        val username: String,
        val password: String
)
