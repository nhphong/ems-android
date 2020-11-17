package com.ea.ems.core.util

import android.view.View

fun View.setVisible(visible: Boolean) {
    if (visible) {
        visible()
    } else {
        gone()
    }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
