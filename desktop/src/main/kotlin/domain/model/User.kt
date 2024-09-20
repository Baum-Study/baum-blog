package domain.model

data class LoginResponse private constructor(
    val token: String,
    val user: User,
    val success: Boolean,
    val message: String,
) {
    companion object {
        fun success(
            token: String,
            user: User,
        ) =
            LoginResponse(token, user, true, "")

        fun failure(
            message: String,
        ) =
            LoginResponse("", User(0, "", ""), false, message)
    }
}

data class User(
    val id: Long,
    val name: String,
    val email: String,
)