package com.averoes.footballapp.utils

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.model.table.TableItem

class TableAdapter(private val context: Context, private val item:List<TableItem>):
    androidx.recyclerview.widget.RecyclerView.Adapter<TableAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Holder =
        Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_table, parent, false))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.bind(item[position])
    }

    class Holder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view){

        private val name:TextView = view.findViewById(R.id.name_table)
        private val played:TextView = view.findViewById(R.id.played)
        private val win:TextView = view.findViewById(R.id.win)
        private val draw:TextView = view.findViewById(R.id.draw)
        private val loss:TextView = view.findViewById(R.id.loss)
        private val total:TextView = view.findViewById(R.id.total)

        fun bind(item:TableItem){

            name.text = item.name
            played.text = item.played.toString()
            win.text = item.win.toString()
            draw.text = item.draw.toString()
            loss.text = item.loss.toString()
            total.text = item.total.toString()

        }
    }
}