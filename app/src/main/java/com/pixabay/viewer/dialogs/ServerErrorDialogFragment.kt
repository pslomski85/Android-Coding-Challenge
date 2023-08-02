package com.pixabay.viewer.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import com.pixabay.viewer.R

class ServerErrorDialogFragment : BaseDialog() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity).let {
            it.setTitle(R.string.server_error_dialog_title)
            it.setMessage(R.string.server_error_dialog_message)
            it.setPositiveButton(R.string.server_error_dialog_button_ok) { _, _ -> dismiss() }
            it.create()
        }
    }
}