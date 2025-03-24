
data class AuthResponse(
    val token: String,
    val refreshToken: String,
    val userId: Int
)

data class UserDataResponse(
    val userId: Int,
    val username: String,
    val email: String
)
