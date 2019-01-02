package com.sokutansupport.studentorder.sokutansupport.processing


fun returnChapterList(numberOfMedia: Int): ArrayList<String> {
    //numberOfMediaでは1がChapter1の文章
    //なので全部分を欲しければ、numberOfMediaに0を指定すればいい
    //stageのまとめを最後につける 141~146

    val chapterList = ArrayList<String>()

    var numberOfChapter = 0
    //あくまで0はデフォルト

    var isSentence: Boolean = false
    //デフォルト。trueが文章で、falseが単語ということ

    //つまりまとめを選択してない時
    if (numberOfMedia <= 140) {

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

        for (i in numberOfChapter..70) {
            chapterList.add("Chapter$i 文章")
            chapterList.add("Chapter$i 単語")
        }

        //ここはそれぞれのstageのまとめを入れる
        for (i in 1..7) {

            //stage2にはまとめがない
            if (i != 2) chapterList.add("Stage$i　まとめ")
        }


    } else {
        //つまりまとめを選択した時


        var numberOfStage: Int = numberOfMedia - 140

        //2が抜けていることを考慮する
        if (numberOfStage > 1) numberOfStage++

        for (i in numberOfStage + 1..7) {
            //stage2にはまとめがない
            if (i != 2) chapterList.add("Stage$i　まとめ")
        }
    }
    return chapterList
}
