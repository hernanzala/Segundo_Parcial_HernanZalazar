package com.example.segundo_parcial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class Adapter (val context: Context) : ListAdapter<Categories, Adapter.ViewHolder>(DiffCallBack) {

    lateinit var onItemClickListener: (Categories) -> Unit

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val name : TextView = view.findViewById(R.id.textViewCategorias)

        fun bind (cat: Categories) {
            name.text = cat.name.toString()

            view.setOnClickListener {
                onItemClickListener(cat)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.itemlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        val equipos = getItem(position)
        holder.bind(equipos)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Categories>() {
        override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return  oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return oldItem == newItem
        }
    }
}