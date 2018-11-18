package com.sokutansupport.studentorder.sokutansupport.fragments

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sokutansupport.studentorder.sokutansupport.R

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
        val view = inflater.inflate(R.layout.fragment_today, container, false)
        return view
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

}
