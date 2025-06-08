package com.pnu.pnuguide.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pnu.pnuguide.network.ChatMessage
import com.pnu.pnuguide.network.ChatRequest
import com.pnu.pnuguide.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val _reply = MutableStateFlow("")
    val reply: StateFlow<String> = _reply

    fun sendMessage(text: String) {
        viewModelScope.launch {
            try {
                val request = ChatRequest(messages = listOf(ChatMessage("user", text)))
                val response = RetrofitClient.openAiService.chat(request)
                                _reply.value = response.choices.firstOrNull()?.message?.content ?: ""
            } catch (e: Exception) {
                                _reply.value = "Error: ${e.message}"
            }
        }
    }
}