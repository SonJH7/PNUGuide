package com.pnu.pnuguide.data

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestoreSettings
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.suspendCancellableCoroutine
import com.pnu.pnuguide.ui.chat.ChatUiMessage

object ChatRepository {
    private val firestore = FirebaseFirestore.getInstance().apply {
        firestoreSettings = firestoreSettings { isPersistenceEnabled = true }
    }

    suspend fun loadMessages(uid: String): List<ChatUiMessage> {
        val snapshot = firestore.collection("chats").document(uid).get().await()
        val list = snapshot.get("messages") as? List<Map<String, Any>> ?: return emptyList()
        return list.map {
            val content = it["content"] as? String ?: ""
            val isUser = it["isUser"] as? Boolean ?: false
            ChatUiMessage(content, isUser)
        }
    }

    suspend fun saveMessages(uid: String, messages: List<ChatUiMessage>) {
        val list = messages.map { mapOf("content" to it.content, "isUser" to it.isUser) }
        firestore.collection("chats").document(uid)
            .set(mapOf("messages" to list))
            .await()
    }

    suspend fun clearMessages(uid: String) {
        firestore.collection("chats").document(uid).delete().await()
    }
}

private suspend fun <T> Task<T>.await(): T = suspendCancellableCoroutine { cont ->
    addOnSuccessListener { cont.resume(it) }
    addOnFailureListener { cont.resumeWithException(it) }
}
