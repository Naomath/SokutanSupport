package com.sokutansupport.studentorder.sokutansupport

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.sokutansupport.studentorder.sokutansupport.fragments.CalendarFragment
import com.sokutansupport.studentorder.sokutansupport.fragments.FragmentPageAdapter
import com.sokutansupport.studentorder.sokutansupport.fragments.ListFragment
import com.sokutansupport.studentorder.sokutansupport.fragments.TodayFragment
import kotlinx.android.synthetic.main.activity_main.*

//上のimport文のおかげでRのidのオブジェクトを直接id名を使って指定できる

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager: FragmentManager = supportFragmentManager
        val adapter :FragmentPageAdapter = FragmentPageAdapter(manager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

    }

}
