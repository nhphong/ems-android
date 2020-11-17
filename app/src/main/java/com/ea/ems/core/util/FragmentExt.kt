package com.ea.ems.core.util

import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    activity?.hideKeyboard()
}

fun Fragment.showKeyboard() {
    activity?.showKeyboard()
}
