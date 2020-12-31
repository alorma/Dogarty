package com.alorma.dogarty.auth

import com.alorma.dogarty.ui.model.AppUser
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class AppAuth(private val auth: FirebaseAuth) {

    fun userId(): String = auth.currentUser?.uid ?: error("User not logged")

    fun auth(): AppUser {
        return if (auth.currentUser != null) {
            auth.currentUser?.let { AppUser.Logged } ?: error("What")
        } else {
            AppUser.NotLogged
        }
    }

    suspend fun login() = suspendCancellableCoroutine<AppUser> { continuation ->
        auth.signInWithEmailAndPassword("bernatbor15@gmail.com", "!")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    auth.currentUser?.let { continuation.resume(AppUser.Logged) }
                } else {
                    continuation.cancel(task.exception)
                }
            }
    }
}