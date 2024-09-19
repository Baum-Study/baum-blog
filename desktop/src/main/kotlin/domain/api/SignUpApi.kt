package domain.api

import domain.util.Validator
import io.ktor.client.*

suspend fun requestToSignUp(
    client: HttpClient,
    email: String,
    password: String,
    confirmPassword: String,
) =
    try {
        Validator.validateEmail(email)
        Validator.validatePassword(password)
        Validator.validatePassword(confirmPassword)
        require(password == confirmPassword) { "비밀번호가 일치하지 않습니다." }

//        val response =
//            client.post("https://example.com/api/login") {
//                contentType(ContentType.Application.Json)
//                setBody(
//                    mapOf(
//                        "email" to email,
//                        "password" to password
//                    )
//                )
//            }
//
//        if (response.status == HttpStatusCode.OK) {
//            val token = response.headers["Authorization"]
//                ?: throw IllegalHeaderValueException("Authorization header not found", 0)
//
//            val body = response.body<User>()
//        }
        true
    } catch (e: Exception) {
        false
    }