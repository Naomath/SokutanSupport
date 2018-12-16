package com.sokutansupport.studentorder.sokutansupport.fragments


import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.DatePicker
import com.sokutansupport.studentorder.sokutansupport.dialogFramgnet.AlertDialogFragment
import com.sokutansupport.studentorder.sokutansupport.R
import com.sokutansupport.studentorder.sokutansupport.classes.OriginalDate
import com.sokutansupport.studentorder.sokutansupport.dialogFramgnet.DatePickDialogFragment
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    companion object : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            Log.d("dd","oko1")
        }

        @JvmStatic
        fun newInstance() = CalendarFragment()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->

            val date: OriginalDate = OriginalDate(year, month, dayOfMonth)
            AlertDialogFragment.newInstance(date).show(fragmentManager, "alert")

            button.setOnClickListener {
                val dialogFragment = DatePickDialogFragment()
                dialogFragment.show(fragmentManager, "dialogFragment")
            }

        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Log.d("dd","oko")
    }

}