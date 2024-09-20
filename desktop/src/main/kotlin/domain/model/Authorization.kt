package domain.model

data class Authorization(
    val accessToken: String?,
    val expiredAt: Long,
) {
    val hasToken: Boolean
        get() = accessToken != null && expiredAt < System.currentTimeMillis()
}