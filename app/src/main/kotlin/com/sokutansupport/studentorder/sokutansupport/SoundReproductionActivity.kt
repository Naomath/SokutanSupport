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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_reproduction)

        numberOfMedia = intent.getIntExtra("MEDIA_NUMBER_KEY", 1)


        //左端のばつボタン
        close_bt.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            //TODO:MediaPlayerを止める　
        }

        //seekbarの下のボタン
        bt_operation.setImageResource(R.drawable.baseline_pause_white_48)

        bt_operation.setOnClickListener {
            //TODO:MediaPlayerの止めるのと再生するのとか中止させたりとかseekbar止まらせたり　
            //TODO:seekBarはmpを止まらせたら必然的に止まる　

            if (isPlaying) {
                bt_operation.setImageResource(R.drawable.baseline_play_arrow_white_48dp)

                isPlaying = false
                /*




                 */
            } else {
                bt_operation.setImageResource(R.drawable.baseline_pause_white_48)

                isPlaying = true
                /*




                 */
            }
        }


        //listView
        val adapter = ArrayAdapter<String>(this, R.layout.list)
        listView.adapter = adapter
        //listViewのitemをタッチして他のMediaに移る時にこのトリガーが引かれる
        listView.setOnItemClickListener { parent, view, position, id ->
            isPlaying = false

            toNextMedia(numberOfMedia + position + 1, adapter)
        }

        playMedia(adapter)
    }


    //mediaを再生するのを中央で管理するメソッド
    fun playMedia(adapter: ArrayAdapter<String>) {

        val mp: MediaPlayer = MediaPlayer.create(this, returnMediaPath())

        mp.setOnCompletionListener {
            isPlaying = false

            toNextMedia(numberOfMedia++, adapter)
        }

        val handler: Handler = Handler()

        setUpViewElements(adapter, mp)

        mp.start()

        isPlaying = true

        playCycle(mp, handler)

        fun releaseMp() {
            mp.release()
        }
    }

    //次のMediaを再生するための準備
    fun toNextMedia(nextValue: Int, adapter: ArrayAdapter<String>) {
        numberOfMedia = nextValue
        //TODO:mediaPlayerの破棄をどうするか。

        playMedia(adapter)
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
    fun playCycle(mp: MediaPlayer, handler: Handler) {
        seekBar.setProgress(mp.currentPosition)

        if (mp.isPlaying) {
            runnable = object : Runnable {
                override fun run() {
                    playCycle(mp, handler)
                }
            }
        }

        //多分ここで1sec遅らせることで１秒ごとに進むようになるはず
        handler.postDelayed(runnable, 1000)

        //おそらく上の操作がないとこのplayCycleが流れる？

    }


    //numberOfMediaを元にしてその該当するmediaのpathをRを使ってInt型で返す
    fun returnMediaPath(): Int {
        //TODO:実際に使うファイルを入れてからかく　　
        val dummyPath: Int = R.raw.sample_music
        return dummyPath
    }


}
