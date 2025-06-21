package com.pnu.pnuguide.ui.course

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CourseSearchAdapter : RecyclerView.Adapter<CourseSearchAdapter.ViewHolder>() {
    private val items = mutableListOf<String>()

    fun submitItems(list: List<String>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun appendItems(list: List<String>) {
        items.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val text: TextView = itemView.findViewById(android.R.id.text1)
        fun bind(value: String) {
            text.text = value
        }
    }
}
