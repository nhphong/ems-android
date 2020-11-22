package com.ea.ems.core.view

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.ea.ems.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

interface DialogUtil {
    fun showProgressDialog(view: View?)
    fun hideProgressDialog()
    fun showDialog(
        view: View?,
        message: String,
        listener: DialogInterface.OnClickListener? = null
    )

    fun showDialog(
        view: View?,
        @StringRes messageResId: Int,
        listener: DialogInterface.OnClickListener? = null
    )
}

class DialogUtilImpl : DialogUtil {

    private var progressDialog: AlertDialog? = null

    override fun showProgressDialog(view: View?) {
        view?.let {
            progressDialog?.dismiss()
            progressDialog = MaterialAlertDialogBuilder(it.context)
                .setView(R.layout.dialog_loading)
                .setCancelable(false)
                .create()
                .apply {
                    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    show()
                }
        }
    }

    override fun hideProgressDialog() {
        progressDialog?.dismiss()
        progressDialog = null
    }

    override fun showDialog(
        view: View?,
        message: String,
        listener: DialogInterface.OnClickListener?
    ) {
        view?.let {
            MaterialAlertDialogBuilder(it.context)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, listener)
                .show()
        }
    }

    override fun showDialog(
        view: View?,
        messageResId: Int,
        listener: DialogInterface.OnClickListener?
    ) {
        showDialog(view, view?.context?.getString(messageResId) ?: "", listener)
    }
}
