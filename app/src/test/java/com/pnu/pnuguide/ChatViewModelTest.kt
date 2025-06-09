package com.pnu.pnuguide

import com.pnu.pnuguide.ui.chat.ChatViewModel
import org.junit.Assert.assertNotNull
import org.junit.Test

class ChatViewModelTest {
    @Test
    fun viewModel_initializes() {
        val vm = ChatViewModel()
        assertNotNull(vm)
    }
}