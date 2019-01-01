package com.sokutansupport.studentorder.sokutansupport.processing

import com.sokutansupport.studentorder.sokutansupport.R

fun returnChapterList(numberOfMedia: Int): ArrayList<String> {
    //numberOfMediaでは1がChapter1の文章
    //なので全部分を欲しければ、numberOfMediaに0を指定すればいい

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
