package com.pnu.pnuguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pnu.pnuguide.ChatViewModel
import com.pnu.pnuguide.ui.theme.PNUGuideTheme
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val label: String, val icon: ImageVector) {
    object Course : Screen("Course", Icons.Filled.Home)
    object Stamp : Screen("Stamp", Icons.Filled.Star)
    object Chat : Screen("Chat", Icons.Filled.Chat)
}

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ChatViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PNUGuideTheme {
                MainScreen(viewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: ChatViewModel) {
    var current by remember { mutableStateOf<Screen>(Screen.Course) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                listOf(Screen.Course, Screen.Stamp, Screen.Chat).forEach { screen ->
                    NavigationBarItem(
                        selected = current == screen,
                        onClick = { current = screen },
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) }
                    )
                }
            }
        }
            ) { innerPadding ->
        when (current) {
            Screen.Course -> CourseScreen(Modifier.padding(innerPadding))
            Screen.Stamp -> StampScreen(Modifier.padding(innerPadding))
            Screen.Chat -> ChatScreen(viewModel, Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun CourseScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Course Screen")
    }
}

@Composable
fun StampScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Stamp Screen")
    }
}

@Composable
fun ChatScreen(viewModel: ChatViewModel, modifier: Modifier = Modifier) {
    val input = remember { mutableStateOf("") }
    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(
            value = input.value,
            onValueChange = { input.value = it },
            label = { Text("Ask something") }
        )
        Button(onClick = { viewModel.sendMessage(input.value) }) {
            Text("Send")
        }
        Text(text = viewModel.reply.value)
    }
}

@Preview(showBackground = true)
@Composable
fun ChatPreview() {
    PNUGuideTheme {
        val vm = ChatViewModel()
        ChatScreen(vm)
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    PNUGuideTheme {
        val vm = ChatViewModel()
        MainScreen(vm)
    }
}