package com.ea.ems.core.util

import android.content.Intent
import com.ea.ems.presentation.base.BaseFragment
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

interface FacebookLogInManager {
    fun logIn(
        fragment: BaseFragment,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    )

    fun logOut()
}

class FacebookLogInManagerImpl(
    private val facebookLoginManager: LoginManager
) : FacebookLogInManager {

    override fun logIn(
        fragment: BaseFragment,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val accessToken = AccessToken.getCurrentAccessToken()
        if (accessToken != null && !accessToken.isExpired) {
            onSuccess.invoke(accessToken.token)
            return
        }

        val callbackManager = CallbackManager.Factory.create()
        fragment.lifeCycleCallbacks.apply {
            onActivityResult = { requestCode: Int, resultCode: Int, data: Intent? ->
                callbackManager.onActivityResult(requestCode, resultCode, data)
            }
        }
        facebookLoginManager
            .registerCallback(
                callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult?) {
                        onSuccess.invoke(result?.accessToken?.token ?: "")
                    }

                    override fun onError(error: FacebookException?) {
                        onError.invoke(error?.message.toString())
                    }

                    override fun onCancel() {}
                })
        facebookLoginManager.logInWithReadPermissions(fragment, listOf(EMAIL, PUBLIC_PROFILE))
    }

    override fun logOut() {
        facebookLoginManager.logOut()
    }
}

private const val EMAIL = "email"
private const val PUBLIC_PROFILE = "public_profile"
