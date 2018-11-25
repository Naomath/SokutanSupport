package com.sokutansupport.studentorder.sokutansupport

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.sokutansupport.studentorder.sokutansupport.classes.OriginalDate

class AlertDialogFragment : DialogFragment() {


    companion object {

        @JvmStatic
        fun newInstance(date: OriginalDate): AlertDialogFragment{
            val alertDialogFragment = AlertDialogFragment()
            val args: Bundle = Bundle()
            alertDialogFragment.arguments = args
            return alertDialogFragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity!!)
                .setTitle("タイトル")
                .setMessage("メッセージ")
                .create()
    }


    override fun onPause() {
        super.onPause()

        // onPause でダイアログを閉じる場合
        dismiss()
    }

}
