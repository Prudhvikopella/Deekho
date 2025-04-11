package com.deekho.app.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.deekho.app.R

class AlternateNameAdapter(
    private val context: Context,
    private val names: List<String>
) : ArrayAdapter<String>(context, 0, names) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_alternate_name, parent, false)
        val textView = view.findViewById<TextView>(R.id.tvAlternateName)
        textView.text = names[position]
        return view
    }
}
