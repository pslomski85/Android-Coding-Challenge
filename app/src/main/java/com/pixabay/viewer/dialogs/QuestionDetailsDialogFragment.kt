package com.pixabay.viewer.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pixabay.viewer.R

class QuestionDetailsDialogFragment : BaseDialog() {

    private val arguments: QuestionDetailsDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(activity).let {
            it.setTitle(R.string.question_details_dialog_title)
            it.setMessage(R.string.question_details_dialog_message)
            it.setPositiveButton(R.string.question_details_dialog_positive_button_caption) { _, _ ->
                onOkClicked(
                    arguments.toBundle()
                )
            }
            it.setNegativeButton(R.string.question_details_dialog_negative_button_caption) { _, _ -> dismiss() }
            it.create()
        }
    }

    private fun onOkClicked(arguments: Bundle?) {
        findNavController().navigate(
            R.id.action_questionDetailsDialogFragment_to_imageDetailsFragment,
            arguments
        )
    }
}