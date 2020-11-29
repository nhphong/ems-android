package com.ea.ems.core.util

import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat.getColor
import com.bumptech.glide.Glide
import com.ea.ems.R

fun ImageView.loadImageUrl(url: String?) {
    Glide.with(this)
        .load(url)
        .error(ColorDrawable(getColor(context, R.color.colorSilver)))
        .into(this)
}
