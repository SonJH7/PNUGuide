package com.pnu.pnuguide

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pnu.pnuguide.network.ChatMessage
import com.pnu.pnuguide.network.ChatRequest
import com.pnu.pnuguide.network.RetrofitClient
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    var reply = mutableStateOf("")
        private set

    fun sendMessage(text: String) {
        viewModelScope.launch {
            try {
                val request = ChatRequest(messages = listOf(ChatMessage("user", text)))
                val response = RetrofitClient.openAiService.chat(request)
                reply.value = response.choices.firstOrNull()?.message?.content ?: ""
            } catch (e: Exception) {
                reply.value = "Error: ${e.message}"
            }
        }
    }
}