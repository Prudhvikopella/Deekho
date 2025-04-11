package com.deekho.app.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.deekho.app.R

class PlaceholderFragment : Fragment(R.layout.activity_home) {

    companion object {
        fun newInstance(title: String): PlaceholderFragment {
            val fragment = PlaceholderFragment()
            val args = Bundle()
            args.putString("title", title)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString("title")
        view.findViewById<TextView>(R.id.tvHome).text = title
    }
}
