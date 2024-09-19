package domain.util

object Validator {
    fun validateEmail(
        value: String,
    ) {
        val isSuccess = value.contains("@bubaum.com")
        if (!isSuccess) throw IllegalArgumentException("Invalid email")
    }

    fun validatePassword(
        value: String,
    ) {
        val isSuccess = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$").matches(value)
        if (!isSuccess) throw IllegalArgumentException("Invalid password")
    }
}