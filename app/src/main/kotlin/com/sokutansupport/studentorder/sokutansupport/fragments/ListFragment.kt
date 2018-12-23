package com.sokutansupport.studentorder.sokutansupport.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.sokutansupport.studentorder.sokutansupport.R
import com.sokutansupport.studentorder.sokutansupport.SoundReproductionActivity
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = ListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val chapterList = ArrayList<String>()
        for (i in 1..50) {
            chapterList.add("Chapter$i 文章")
            chapterList.add("Chapter$i 単語")
            //これで文字列結合
        }


        val arrayAdapter = ArrayAdapter<String>(activity, R.layout.list, chapterList)
        list.adapter = arrayAdapter

        list.setOnItemClickListener { parent, view, position, id ->

            val intent = Intent(activity, SoundReproductionActivity::class.java)
            intent.putExtra("MEDIA_NUMBER_KEY", position + 1)
            startActivity(intent)
        }
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }


}
