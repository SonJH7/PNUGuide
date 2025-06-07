package com.pnu.pnuguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pnu.pnuguide.ChatViewModel
import com.pnu.pnuguide.ui.theme.PNUGuideTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ChatViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PNUGuideTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChatScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
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