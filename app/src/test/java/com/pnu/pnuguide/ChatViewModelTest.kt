package com.pnu.pnuguide

import com.pnu.pnuguide.ui.chat.ChatViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ChatViewModelTest {

    @Test
    fun `sendMessage updates reply`() = runTest {
        val vm = ChatViewModel()
        vm.sendMessage("Hello")
        assertTrue(vm.reply.value.isNotEmpty())
    }
}