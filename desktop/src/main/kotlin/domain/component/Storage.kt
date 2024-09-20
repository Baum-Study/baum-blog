package domain.component

import domain.model.Authorization
import java.util.prefs.Preferences

fun getTokenFromPreferences(): Authorization {
    val token: String? = Preferences.userRoot().get(AUTH_TOKEN, null)
    val expiredAt = Preferences.userRoot().getLong(EXPIRED_AT, 0)
    return Authorization(token, expiredAt)
}

fun saveTokenToPreferences(
    token: String,
    expiredAt: Long,
) {
    Preferences.userRoot().put(AUTH_TOKEN, token)
    Preferences.userRoot().putLong(EXPIRED_AT, expiredAt)
}

fun logout() {
    Preferences.userRoot().remove(AUTH_TOKEN)
    Preferences.userRoot().remove(EXPIRED_AT)
}

private const val AUTH_TOKEN = "auth_token"
private const val EXPIRED_AT = "expired_at"