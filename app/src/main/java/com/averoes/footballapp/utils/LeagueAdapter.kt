package com.averoes.footballapp.utils

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.view.activity.LeagueActivity
import com.bumptech.glide.Glide
import com.example.footballapp.mvp.model.soccer.CountrysItem
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.find
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

class LeagueAdapter(private val context: Context, private val item: List<CountrysItem>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<LeagueAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Holder =
        Holder(LayoutInflater.from(parent.context).inflate(R.layout.league_item, parent, false))


    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(context, item[position])
    }


    class Holder(override val containerView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        private val name: TextView = containerView.find(R.id.league_name)
        private val image: ImageView = containerView.find(R.id.league_img)

        fun bind(context: Context, items: CountrysItem) {
            name.text = items.leagueName
            Glide.with(context).load(items.leagueBadge).into(image)

            itemView.setOnClickListener {
                context.startActivity(itemView.context.intentFor<LeagueActivity>("detail" to items).newTask())
            }

        }

    }
}