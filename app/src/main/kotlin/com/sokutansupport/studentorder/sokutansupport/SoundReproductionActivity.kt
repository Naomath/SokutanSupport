package com.sokutansupport.studentorder.sokutansupport

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sound_reproduction.*

class SoundReproductionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_reproduction)

        setUpViews()

        val chater_number: Int = intent.getIntExtra("CHAPTER_NUMBER_KEY", 1)

    }

    fun setUpViews(){

        close_bt.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}
