package com.sokutansupport.studentorder.sokutansupport.fragments

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FragmentPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> return TodayFragment.newInstance()
            1 -> return CalendarFragment.newInstance()
            2 -> return ListFragment.newInstance()
        }

        return null

    }

    // ページ数
    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {

        when (position) {
            0 -> return "Today"
            1 -> return "Calendar"
            2 -> return "List"
        }

        return null
    }
}