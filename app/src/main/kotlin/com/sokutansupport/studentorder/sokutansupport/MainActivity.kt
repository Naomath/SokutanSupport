package com.sokutansupport.studentorder.sokutansupport

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.sokutansupport.studentorder.sokutansupport.fragments.FragmentPageAdapter
import kotlinx.android.synthetic.main.activity_main.*

//上のimport文のおかげでRのidのオブジェクトを直接id名を使って指定できる

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager: FragmentManager = supportFragmentManager
        val adapter: FragmentPageAdapter = FragmentPageAdapter(manager)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 2
        tabLayout.setupWithViewPager(viewPager)

    }

}
