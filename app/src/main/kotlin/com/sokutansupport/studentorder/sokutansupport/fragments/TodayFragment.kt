package com.sokutansupport.studentorder.sokutansupport.fragments

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.sokutansupport.studentorder.sokutansupport.R
import kotlinx.android.synthetic.main.fragment_today.*


class TodayFragment : Fragment() {


    companion object {

        @JvmStatic
        //上の奴は.javaのファイルからもこのメソッドにアクセスできるようにしている
        fun newInstance() = TodayFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_today, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //このメソッドのviewはonCreateView()の返値
        //このメソッドを使う利点はsyntheticが使える
        val dummyItems = arrayOf("Android", "iOS", "Windows", "macOS", "Unix","Android", "iOS", "Windows", "macOS", "Unix")
        //TODO:ここでちゃんとしたリストの取得をする:

        val arrayAdapter = ArrayAdapter<String>(activity, R.layout.list, dummyItems)
        list.adapter = arrayAdapter
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

}
