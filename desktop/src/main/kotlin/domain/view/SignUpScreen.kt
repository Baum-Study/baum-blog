package domain.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import domain.api.requestToSignUp
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import kotlinx.coroutines.withContext
import javax.swing.JOptionPane

@Composable
@Preview
fun signUpScreen(
    onSignUpSuccess: () -> Unit,
    backToLogin: () -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val client = remember { HttpClient(CIO) }

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Sign Up", style = MaterialTheme.typography.h5)

            Spacer(Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(0.4f),
            )

            Spacer(Modifier.height(8.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(0.4f),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
            )

            Spacer(Modifier.height(16.dp))

            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth(0.4f),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
            )

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    CoroutineScope(IO).launch {
                        val signUpSuccess = requestToSignUp(client, email, password, confirmPassword)
                        withContext(Dispatchers.Swing) {
                            if (signUpSuccess) {
                                onSignUpSuccess.invoke()
                            } else {
                                JOptionPane.showMessageDialog(
                                    null,
                                    "회원가입에 실패했습니다.",
                                    "Sign Up Error",
                                    JOptionPane.ERROR_MESSAGE
                                )
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(0.4f)
            ) {
                Text("Sign Up")
            }

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = { backToLogin.invoke() },
                modifier = Modifier.fillMaxWidth(0.4f)
            ) {
                Text("Back to Login")
            }
        }
    }
}