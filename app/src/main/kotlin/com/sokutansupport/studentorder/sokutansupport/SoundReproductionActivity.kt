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
    
    //TODO:それぞれのステージのまとめについても書く　

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

            if (numberOfMedia != 146){

                //最後じゃない時

                numberOfMedia++

                mp = MediaPlayer.create(this, returnMediaPath(numberOfMedia))

                toNextMedia(mp, adapter)
            }else{

                //最後の時
                //元のリストの画面に戻る
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }


        }

        //リスナーたち

        //左端のばつボタン
        close_bt.setOnClickListener {
            mp.release()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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

            numberOfMedia = numberOfMedia + position + 1

            mp = MediaPlayer.create(this, returnMediaPath(numberOfMedia))

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
        if (numberOfMedia <= 140) {

            //まずはまとめ以外のが選択された時

            if (numberOfMedia % 2 != 0) {
                //つまり選択されたのが文章の時
                titleTextView.setText("Chapter${numberOfMedia / 2 + 1} 文章")

            } else {
                //つまり選択されたのが単語な時
                titleTextView.setText("Chapter${numberOfMedia / 2} 単語")

            }
        } else {

            //まとめが選択された時
            var numberOfStage: Int = numberOfMedia - 140

            //2が抜けていることを考慮する
            if (numberOfStage > 1) numberOfStage++

            titleTextView.setText("Stage$numberOfStage まとめ")

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


    fun returnMediaPath(numberOfMedia: Int): Int {
        //mediaの順番を引数にとってmusicPathを返す
        //TODO:コードとして汚そう　

        when (numberOfMedia) {
            1 -> return R.raw.track1
            2 -> return R.raw.track2
            3 -> return R.raw.track3
            4 -> return R.raw.track4
            5 -> return R.raw.track5
            6 -> return R.raw.track6
            7 -> return R.raw.track7
            8 -> return R.raw.track8
            9 -> return R.raw.track9
            10 -> return R.raw.track10
            11 -> return R.raw.track11
            12 -> return R.raw.track12
            13 -> return R.raw.track13
            14 -> return R.raw.track14
            15 -> return R.raw.track15
            16 -> return R.raw.track16
            17 -> return R.raw.track17
            18 -> return R.raw.track18
            19 -> return R.raw.track19
            20 -> return R.raw.track20
            21 -> return R.raw.track21
            22 -> return R.raw.track22
            23 -> return R.raw.track23
            24 -> return R.raw.track24
            25 -> return R.raw.track25
            26 -> return R.raw.track26
            27 -> return R.raw.track27
            28 -> return R.raw.track28
            29 -> return R.raw.track29
            30 -> return R.raw.track30
            31 -> return R.raw.track31
            32 -> return R.raw.track32
            33 -> return R.raw.track33
            34 -> return R.raw.track34
            35 -> return R.raw.track35
            36 -> return R.raw.track36
            37 -> return R.raw.track37
            38 -> return R.raw.track38
            39 -> return R.raw.track39
            40 -> return R.raw.track40
            41 -> return R.raw.track41
            42 -> return R.raw.track42
            43 -> return R.raw.track43
            44 -> return R.raw.track44
            45 -> return R.raw.track45
            46 -> return R.raw.track46
            47 -> return R.raw.track47
            48 -> return R.raw.track48
            49 -> return R.raw.track49
            50 -> return R.raw.track50
            51 -> return R.raw.track51
            52 -> return R.raw.track52
            53 -> return R.raw.track53
            54 -> return R.raw.track54
            55 -> return R.raw.track55
            56 -> return R.raw.track56
            57 -> return R.raw.track57
            58 -> return R.raw.track58
            59 -> return R.raw.track59
            60 -> return R.raw.track60
            61 -> return R.raw.track61
            62 -> return R.raw.track62
            63 -> return R.raw.track63
            64 -> return R.raw.track64
            65 -> return R.raw.track65
            66 -> return R.raw.track66
            67 -> return R.raw.track67
            68 -> return R.raw.track68
            69 -> return R.raw.track69
            70 -> return R.raw.track70
            71 -> return R.raw.track71
            72 -> return R.raw.track72
            73 -> return R.raw.track73
            74 -> return R.raw.track74
            75 -> return R.raw.track75
            76 -> return R.raw.track76
            77 -> return R.raw.track77
            78 -> return R.raw.track78
            79 -> return R.raw.track79
            80 -> return R.raw.track80
            81 -> return R.raw.track81
            82 -> return R.raw.track82
            83 -> return R.raw.track83
            84 -> return R.raw.track84
            85 -> return R.raw.track85
            86 -> return R.raw.track86
            87 -> return R.raw.track87
            88 -> return R.raw.track88
            89 -> return R.raw.track89
            90 -> return R.raw.track90
            91 -> return R.raw.track91
            92 -> return R.raw.track92
            93 -> return R.raw.track93
            94 -> return R.raw.track94
            95 -> return R.raw.track95
            96 -> return R.raw.track96
            97 -> return R.raw.track97
            98 -> return R.raw.track98
            99 -> return R.raw.track99
            100 -> return R.raw.track100
            101 -> return R.raw.track101
            102 -> return R.raw.track102
            103 -> return R.raw.track103
            104 -> return R.raw.track104
            105 -> return R.raw.track105
            106 -> return R.raw.track106
            107 -> return R.raw.track107
            108 -> return R.raw.track108
            109 -> return R.raw.track109
            110 -> return R.raw.track110
            111 -> return R.raw.track111
            112 -> return R.raw.track112
            113 -> return R.raw.track113
            114 -> return R.raw.track114
            115 -> return R.raw.track115
            116 -> return R.raw.track116
            117 -> return R.raw.track117
            118 -> return R.raw.track118
            119 -> return R.raw.track119
            120 -> return R.raw.track120
            121 -> return R.raw.track121
            122 -> return R.raw.track122
            123 -> return R.raw.track123
            124 -> return R.raw.track124
            125 -> return R.raw.track125
            126 -> return R.raw.track126
            127 -> return R.raw.track127
            128 -> return R.raw.track128
            129 -> return R.raw.track129
            130 -> return R.raw.track130
            131 -> return R.raw.track131
            132 -> return R.raw.track132
            133 -> return R.raw.track133
            134 -> return R.raw.track134
            135 -> return R.raw.track135
            136 -> return R.raw.track136
            137 -> return R.raw.track137
            138 -> return R.raw.track138
            139 -> return R.raw.track139
            140 -> return R.raw.track140
            141 -> return R.raw.stage1
            142 -> return R.raw.stage3
            143 -> return R.raw.stage4
            144 -> return R.raw.stage5
            145 -> return R.raw.stage6
            146 -> return R.raw.stage7
            //このelseが使われることはないはず
            //便宜上
            else -> return 0
        }
    }


}
