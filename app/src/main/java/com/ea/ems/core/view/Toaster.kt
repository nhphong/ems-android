package com.ea.ems.core.view

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

interface Toaster {
    fun display(message: String, duration: Int)
    fun display(message: String)
    fun display(@StringRes messageResId: Int, duration: Int)
    fun display(@StringRes messageResId: Int)
}

class ToasterImpl(private val context: Context) : Toaster {

    private var toast: Toast? = null

    override fun display(message: String, duration: Int) {
        toast?.cancel()
        toast = Toast.makeText(context, message, duration)
        toast?.show()
    }

    override fun display(message: String) {
        display(message, Toast.LENGTH_SHORT)
    }

    override fun display(@StringRes messageResId: Int, duration: Int) {
        display(context.getString(messageResId), duration)
    }

    override fun display(@StringRes messageResId: Int) {
        display(messageResId, Toast.LENGTH_SHORT)
    }
}
