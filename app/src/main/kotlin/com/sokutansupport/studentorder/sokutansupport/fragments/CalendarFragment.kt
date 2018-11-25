package com.sokutansupport.studentorder.sokutansupport.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sokutansupport.studentorder.sokutansupport.AlertDialogFragment
import com.sokutansupport.studentorder.sokutansupport.R
import com.sokutansupport.studentorder.sokutansupport.classes.OriginalDate
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment : Fragment() {

    companion object {

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
        }
    }


}
