package com.sokutansupport.studentorder.sokutansupport

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.sokutansupport.studentorder.sokutansupport.fragments.FragmentPageAdapter
import com.sokutansupport.studentorder.sokutansupport.fragments.ListFragment
import kotlinx.android.synthetic.main.activity_main.*

//上のimport文のおかげでRのidのオブジェクトを直接id名を使って指定できる

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment :Fragment = ListFragment()
        val transaction :FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frame,fragment)
        transaction.commit()
    }

}
