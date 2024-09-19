import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import domain.view.*
import domain.view.Screen.*

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Baum Blog",
        state = WindowState(
            width = 1280.dp,
            height = 840.dp,
            placement = WindowPlacement.Floating,
        ),
    ) {
        var currentScreen by remember { mutableStateOf(LOGIN) }
        val showTopAppBar = currentScreen != LOGIN && currentScreen != SIGN_UP
        MaterialTheme {
            Scaffold(
                topBar = {
                    if (showTopAppBar) {
                        TopAppBar(
                            title = { Text("Baum Blog") },
                            modifier = Modifier.clickable {
                                currentScreen = HOME
                            },
                            actions = {
                                TextButton(onClick = { currentScreen = CREATE_POST }) {
                                    Text("게시글 등록", color = MaterialTheme.colors.onPrimary)
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                TextButton(onClick = {
                                    currentScreen = LOGIN
                                }) {
                                    Text("로그아웃", color = MaterialTheme.colors.onPrimary)
                                }
                            }
                        )
                    }
                },
                content = {
                    when (currentScreen) {
                        LOGIN -> loginScreen(
                            onLoginSuccess = { currentScreen = HOME },
                            moveToSignUp = { currentScreen = SIGN_UP },
                        )

                        SIGN_UP -> signUpScreen(
                            onSignUpSuccess = { currentScreen = HOME },
                            backToLogin = { currentScreen = LOGIN },
                        )

                        HOME -> homeScreen()
                        CREATE_POST -> createPostScreen()
                        UPDATE_POST -> updatePostScreen()
                    }
                }
            )
        }
    }
}