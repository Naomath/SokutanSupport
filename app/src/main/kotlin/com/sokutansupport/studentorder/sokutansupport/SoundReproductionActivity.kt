package com.sokutansupport.studentorder.sokutansupport

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_sound_reproduction.*

class SoundReproductionActivity : AppCompatActivity() {

    /*
   onCreate()-----setUpViewElements()-----returnListViewContents()

   次のMediaに自動で移る-----------?????????????????????????????????
                                                                  -----setUpViewElements()-----returnListViewContents()
   再生Listの中のMediaにタッチ-----listViewのsetOnItemClickListener


     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_reproduction)

        //この変数は受け取ったやつなのでめっちゃ重要
        val numberOfMedia: Int = intent.getIntExtra("MEDIA_NUMBER_KEY", 1)


        //ここで諸々のviewの初期設定をする
        close_bt.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val adapter = ArrayAdapter<String>(this, R.layout.list)
        listView.adapter = adapter

        //listViewのitemをタッチして他のMediaに移る時にこのトリガーが引かれる
        listView.setOnItemClickListener { parent, view, position, id ->

            setUpViewElements(numberOfMedia + position + 1, adapter)
        }


        setUpViewElements(numberOfMedia, adapter)
    }


    //viewの中身の設定
    fun setUpViewElements(numberOfMedia: Int, adapter: ArrayAdapter<String>) {

        //このif文はtitleというtextViewの設定に使う
        if (numberOfMedia % 2 != 0) {
            //つまり選択されたのが文章の時
            titleTextView.setText("Chapter${numberOfMedia / 2 + 1} 文章")

        } else {
            //つまり選択されたのが単語な時
            titleTextView.setText("Chapter${numberOfMedia / 2} 単語")

        }

        //listViewのitemの設定
        val chapterList: ArrayList<String> = returnListViewContents(numberOfMedia)
        adapter.clear()
        adapter.addAll(chapterList)

        //TODO:SeekBarなどの設定　
        /*








         */
    }


    //listviewの中身を設定する
    fun returnListViewContents(numberOfMedia: Int): ArrayList<String> {

        val chapterList = ArrayList<String>()

        var numberOfChapter = 0
        //あくまで0はデフォルト

        var isSentence: Boolean = false
        //デフォルト。trueが文章で、falseが単語ということ

        if (numberOfMedia % 2 != 0) {
            //つまり選択されたのが文章な時
            numberOfChapter = (numberOfMedia - 1) / 2 + 1
            isSentence = true

        } else {
            //つまりは選択されたのが単語な時
            numberOfChapter = numberOfMedia / 2
            isSentence = false

        }

        //これは再生されているのが文章の時のためのif
        if (isSentence) {
            chapterList.add("Chapter$numberOfChapter 単語")
        }

        numberOfChapter++

        for (i in numberOfChapter..50) {
            chapterList.add("Chapter$i 文章")
            chapterList.add("Chapter$i 単語")
        }

        //このif文はchapter50の単語のが再生さレている時
        if (numberOfChapter == 51) {
            chapterList.add("Nothing")
        }

        return chapterList
    }

}
