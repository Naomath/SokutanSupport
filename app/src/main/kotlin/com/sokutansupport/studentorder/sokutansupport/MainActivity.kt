package com.sokutansupport.studentorder.sokutansupport

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.sokutansupport.studentorder.sokutansupport.fragments.CalendarFragment
import com.sokutansupport.studentorder.sokutansupport.fragments.ListFragment
import com.sokutansupport.studentorder.sokutansupport.fragments.TodayFragment
import kotlinx.android.synthetic.main.activity_main.*
//上のimport文のおかげでRのidのオブジェクトを直接id名を使って指定できる

class MainActivity : AppCompatActivity() {

    val TODAY: Int = 0
    val CALENDAR: Int = 1
    val LIST: Int = 2
    //それぞれのfragmentの状態を表すときに使う

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_today -> {
                setOutFragment(TODAY)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_calendar -> {
                setOutFragment(CALENDAR)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_list -> {
                setOutFragment(LIST)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        setOutFragment(TODAY)
    }

    fun setOutFragment(mode: Int) {
        val transaction = supportFragmentManager.beginTransaction()

        when(mode){
            TODAY -> transaction.add(R.id.frame, TodayFragment.newInstance())

            CALENDAR -> transaction.add(R.id.frame, CalendarFragment.newInstance())

            LIST -> transaction.add(R.id.frame, ListFragment.newInstance())
        }
        transaction.commit()
    }
}
