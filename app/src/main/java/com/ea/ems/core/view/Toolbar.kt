package com.ea.ems.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getColor
import com.ea.ems.R
import com.ea.ems.core.util.setVisible
import kotlinx.android.synthetic.main.toolbar.view.*

class Toolbar : ConstraintLayout {

    var onBackPressed: (() -> Unit)? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        View.inflate(context, R.layout.toolbar, this)
        val styles = context.obtainStyledAttributes(attrs, R.styleable.Toolbar)

        try {
            title = styles.getString(R.styleable.Toolbar_title).orEmpty()
            styles.getBoolean(R.styleable.Toolbar_showUpButton, true).let {
                ivToolbarUp.setVisible(it)
            }
            styles.getBoolean(R.styleable.Toolbar_showLogo, false).let {
                tvToolbarTitle.setVisible(!it)
                ivToolbarLogo.setVisible(it)
            }
            if (background == null) {
                setBackgroundColor(getColor(context, R.color.colorGreenAtlantis))
            }
        } finally {
            styles.recycle()
        }
    }

    var title: String
        get() = tvToolbarTitle.text.toString()
        set(value) {
            tvToolbarTitle.text = value
        }
}
