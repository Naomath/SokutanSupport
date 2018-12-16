package com.sokutansupport.studentorder.sokutansupport.dialogFramgnet

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.sokutansupport.studentorder.sokutansupport.R
import com.sokutansupport.studentorder.sokutansupport.MainActivity
import android.app.DatePickerDialog
import android.support.annotation.NonNull
import com.sokutansupport.studentorder.sokutansupport.fragments.CalendarFragment
import java.util.*


class DatePickDialogFragment: DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity!!,year, month, day)
    }

    override fun onDateSet(view: android.widget.DatePicker, year: Int,
                           monthOfYear: Int, dayOfMonth: Int) {
    }

}
