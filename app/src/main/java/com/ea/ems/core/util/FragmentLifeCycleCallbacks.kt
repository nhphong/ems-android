package com.ea.ems.core.util

import android.content.Intent

@Deprecated("Use registerForActivityResult(ActivityResultContract, ActivityResultCallback) instead")
class FragmentLifeCycleCallbacks {

    var onActivityResult: ((requestCode: Int, resultCode: Int, data: Intent?) -> Unit)? = null

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        onActivityResult?.invoke(requestCode, resultCode, data)
    }
}
