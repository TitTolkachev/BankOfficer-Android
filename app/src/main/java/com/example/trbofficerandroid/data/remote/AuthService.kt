package com.example.trbofficerandroid.data.remote

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.example.trbofficerandroid.R
import com.example.trbofficerandroid.data.remote.model.UserData
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

class AuthService(
    private val context: Context,
    private val auth: FirebaseAuth,
    private val googleClient: SignInClient,
) {
    suspend fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun getGoogleSignInIntentSender(): IntentSender? {
        val result = try {
            googleClient.beginSignIn(buildGoogleSignInRequest()).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }

        return result?.pendingIntent?.intentSender
    }

    suspend fun signInWithGoogle(intent: Intent) {
        val credential = googleClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)

        auth.signInWithCredential(googleCredentials).await()
    }

    suspend fun signOut() {
        try {
            googleClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    suspend fun resetPassword(email: String) {
        try {
            auth.sendPasswordResetEmail(email).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    suspend fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            token = getIdToken(false).await().token,
            username = displayName,
            profilePictureUrl = photoUrl?.toString()
        )
    }

    private fun buildGoogleSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}