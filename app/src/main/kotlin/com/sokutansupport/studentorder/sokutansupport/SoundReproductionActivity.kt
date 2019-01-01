package com.sokutansupport.studentorder.sokutansupport

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import android.widget.SeekBar
import com.sokutansupport.studentorder.sokutansupport.processing.returnChapterList
import kotlinx.android.synthetic.main.activity_sound_reproduction.*


class SoundReproductionActivity : AppCompatActivity() {

    var runnable: Runnable? = null


    //1はdefault値
    //この変数は受け取ったやつなのでめっちゃ重要
    //その時再生されているメディアの番号になる
    var numberOfMedia: Int = 1


    //今mediaがプレイしているかどうか
    //本当はMediaPlayerのインスタンスにisPlayingでわかるけど、まだそれは無理っぽい
    var isPlaying: Boolean = false

    var handler: Handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_reproduction)

        numberOfMedia = intent.getIntExtra("MEDIA_NUMBER_KEY", 1)

        //listView
        val adapter = ArrayAdapter<String>(this, R.layout.list)
        listView.adapter = adapter

        //define media player
        var mp: MediaPlayer = MediaPlayer.create(this, returnMediaPath(numberOfMedia))

        mp.setOnCompletionListener {
            isPlaying = false

            handler.removeCallbacks(runnable)

            handler = Handler()

            mp.release()

            mp = MediaPlayer.create(this, returnMediaPath(numberOfMedia++))

            numberOfMedia = numberOfMedia++

            toNextMedia(mp, adapter)
        }

        //リスナーたち

        //左端のばつボタン
        close_bt.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            mp.release()
        }

        //seekbarの下のボタン
        bt_operation.setImageResource(R.drawable.baseline_pause_white_48)

        bt_operation.setOnClickListener {

            if (isPlaying) {
                bt_operation.setImageResource(R.drawable.baseline_play_arrow_white_48dp)

                isPlaying = false

                mp.pause()
            } else {
                bt_operation.setImageResource(R.drawable.baseline_pause_white_48)

                isPlaying = true
                mp.start()
            }
        }

        //listViewのitemをタッチして他のMediaに移る時にこのトリガーが引かれる
        listView.setOnItemClickListener { parent, view, position, id ->
            isPlaying = false

            handler.removeCallbacks(runnable)

            handler = Handler()

            mp.release()

            mp = MediaPlayer.create(this, returnMediaPath(numberOfMedia + position + 1))

            numberOfMedia = numberOfMedia + position + 1

            toNextMedia(mp, adapter)
        }


        playMedia(mp, adapter)
    }

    override fun onBackPressed() {}

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    //mediaを再生するのを中央で管理するメソッド
    fun playMedia(mp: MediaPlayer, adapter: ArrayAdapter<String>) {

        setUpViewElements(adapter, mp)

        mp.start()

        isPlaying = true

        playCycle(mp)
    }

    //次のMediaを再生するための準備
    fun toNextMedia(mp: MediaPlayer, adapter: ArrayAdapter<String>) {
        playMedia(mp, adapter)
    }


    //viewの中身の設定
    fun setUpViewElements(adapter: ArrayAdapter<String>, mp: MediaPlayer) {

        //このif文はtitleというtextViewの設定に使う
        if (numberOfMedia % 2 != 0) {
            //つまり選択されたのが文章の時
            titleTextView.setText("Chapter${numberOfMedia / 2 + 1} 文章")

        } else {
            //つまり選択されたのが単語な時
            titleTextView.setText("Chapter${numberOfMedia / 2} 単語")

        }

        //listViewのitemの設定
        val chapterList: ArrayList<String> = returnChapterList(numberOfMedia)
        adapter.clear()
        adapter.addAll(chapterList)

        seekBar.max = mp.duration

        seekBar.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                            seekBar: SeekBar,
                            progress: Int, fromUser: Boolean
                    ) {
                        //ツマミをドラッグした時に呼ばれる

                        //fromUserはユーザーの操作によって変えられた場合にtrue
                        if (fromUser) {
                            mp.seekTo(progress)
                            //seekTo()での引数はミリ秒なので直接progressをとってきてでよき
                        }
                    }


                    override fun onStartTrackingTouch(seekBar: SeekBar) {
                        // ツマミに触れたときに呼ばれる
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar) {
                        // ツマミを離したときに呼ばれる
                    }
                }
        )

    }

    //seekBarを進め続けるためのメソッド
    fun playCycle(mp: MediaPlayer) {

        if (isPlaying) seekBar.setProgress(mp.currentPosition)

        if (mp.isPlaying) {
            runnable = object : Runnable {
                override fun run() {
                    if (isPlaying) playCycle(mp)
                }
            }
        }

     //多分ここで1sec遅らせることで１秒ごとに進むようになるはず
        handler.postDelayed(runnable, 1000)

        //おそらく上の操作がないとこのplayCycleが流れる？

    }


/*
    fun returnMediaPath(numberOfMedia: Int): Int{
        //mediaの順番を引数にとってmusicPathを返す
        //TODO:コードとして汚そう　

        when(numberOfMedia){
            1 -> return R.raw.
            2 -> return R.raw.
                3 -> return R.raw.
                4 -> return R.raw.
                5 -> return R.raw.
                6 -> return R.raw.
                7 -> return R.raw.
                8 -> return R.raw.
                9 -> return R.raw.
                10 -> return R.raw.
                11 -> return R.raw.
                12 -> return R.raw.
                13 -> return R.raw.
                14 -> return R.raw.
                15 -> return R.raw.
                16 -> return R.raw.
                17 -> return R.raw.
                18 -> return R.raw.
                19 -> return R.raw.
                20 -> return R.raw.
                21 -> return R.raw.
                22 -> return R.raw.
                23 -> return R.raw.
                24 -> return R.raw.
                25 -> return R.raw.
                26 -> return R.raw.
                27 -> return R.raw.
                28 -> return R.raw.
                29 -> return R.raw.
                30 -> return R.raw.
                31 -> return R.raw.
                32 -> return R.raw.
                33 -> return R.raw.
                34 -> return R.raw.
                35 -> return R.raw.
                36 -> return R.raw.
                37 -> return R.raw.
                38 -> return R.raw.
                39 -> return R.raw.
                40 -> return R.raw.
                41 -> return R.raw.
                42 -> return R.raw.
                43 -> return R.raw.
                44 -> return R.raw.
                45 -> return R.raw.
                46 -> return R.raw.
                47 -> return R.raw.
                48 -> return R.raw.
                49 -> return R.raw.
                50 -> return R.raw.
                51 -> return R.raw.
                52 -> return R.raw.
                53 -> return R.raw.
                54 -> return R.raw.
                55 -> return R.raw.
                56 -> return R.raw.
                57 -> return R.raw.
                58 -> return R.raw.
                59 -> return R.raw.
                60 -> return R.raw.
                61 -> return R.raw.
                62 -> return R.raw.
                63 -> return R.raw.
                64 -> return R.raw.
                65 -> return R.raw.
                66 -> return R.raw.
                67 -> return R.raw.
                68 -> return R.raw.
                69 -> return R.raw.
                70 -> return R.raw.
                71 -> return R.raw.
                72 -> return R.raw.
                73 -> return R.raw.
                74 -> return R.raw.
                75 -> return R.raw.
                76 -> return R.raw.
                77 -> return R.raw.
                78 -> return R.raw.
                79 -> return R.raw.
                80 -> return R.raw.
                81 -> return R.raw.
                82 -> return R.raw.
                83 -> return R.raw.
                84 -> return R.raw.
                85 -> return R.raw.
                86 -> return R.raw.
                87 -> return R.raw.
                88 -> return R.raw.
                89 -> return R.raw.
                90 -> return R.raw.
                91 -> return R.raw.
                92 -> return R.raw.
                93 -> return R.raw.
                94 -> return R.raw.
                95 -> return R.raw.
                96 -> return R.raw.
                97 -> return R.raw.
                98 -> return R.raw.
                99 -> return R.raw.
                100 -> return R.raw.sample_music


    }
    */





}
