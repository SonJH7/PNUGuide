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

    private val _messages = MutableStateFlow<List<ChatUiMessage>>(emptyList())
    val messages: StateFlow<List<ChatUiMessage>> = _messages

    fun sendMessage(text: String) {
        val userMsg = ChatUiMessage(text, true)
        _messages.value = _messages.value + userMsg
        viewModelScope.launch {
            try {
                val requestMessages = _messages.value.map {
                    ChatMessage(if (it.isUser) "user" else "assistant", it.content)
                }
                val request = ChatRequest(messages = requestMessages)
                val response = RetrofitClient.openAiService.chat(request)
                val reply = response.choices.firstOrNull()?.message?.content ?: ""
                _messages.value = _messages.value + ChatUiMessage(reply, false)
            } catch (e: Exception) {
                _messages.value = _messages.value + ChatUiMessage("Error: ${e.message}", false)
            }
        }
    }
}
