package cz.upce.nnpia.personstatistics.security

enum class UserPermission(
	val permission: String
) {
	PROFILE_READ("profile:read"),
	PROFILE_UPDATE("profile:update"),
	PROFILE_DELETE("profile:delete"),
	PROFILE_CREATE("profile:create")
}
