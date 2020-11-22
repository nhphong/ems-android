package com.ea.ems.core.util

import android.app.Activity
import androidx.activity.result.ActivityResult
import com.ea.ems.presentation.base.BaseFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import timber.log.Timber

interface GoogleSignInManager {
    fun signIn(
        fragment: BaseFragment,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    )

    fun signOut()
}

class GoogleSignInManagerImpl(
    private val signInClient: GoogleSignInClient
) : GoogleSignInManager {

    override fun signIn(
        fragment: BaseFragment,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val existingAccount = GoogleSignIn.getLastSignedInAccount(fragment.context)
        if (existingAccount != null) {
            onSuccess(existingAccount.idToken.orEmpty())
            return
        }

        fragment.apply {
            activityResultCallback = { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    try {
                        val account = task.getResult(ApiException::class.java)!!
                        Timber.d("firebaseAuthWithGoogle: ${account.id}")
                        onSuccess(account.idToken.orEmpty())
                    } catch (exception: ApiException) {
                        Timber.e(exception)
                        onError(MSG_INTERNAL_ERROR)
                    }
                }
            }
            activityResultLauncher.launch(signInClient.signInIntent)
        }
    }

    override fun signOut() {
        signInClient.signOut()
    }
}
