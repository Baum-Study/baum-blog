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
import domain.api.requestToLogin
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import kotlinx.coroutines.withContext
import javax.swing.JOptionPane

@Composable
@Preview
fun loginScreen(
    onLoginSuccess: () -> Unit,
    moveToSignUp: () -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val client = remember { HttpClient(CIO) }

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Login", style = MaterialTheme.typography.h5)

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

            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
//                        val login = requestToLogin(client, email, password)
                        withContext(Dispatchers.Swing) {
//                            if (login.success) {
                                onLoginSuccess.invoke()
//                            } else {
//                                JOptionPane.showMessageDialog(
//                                    null,
//                                    login.message,
//                                    "Login Error",
//                                    JOptionPane.ERROR_MESSAGE
//                                )
//                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(0.4f)
            ) {
                Text("Login")
            }

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = moveToSignUp,
                modifier = Modifier.fillMaxWidth(0.4f)
            ) {
                Text("Sign Up")
            }

            Spacer(Modifier.height(8.dp))
        }
    }
}