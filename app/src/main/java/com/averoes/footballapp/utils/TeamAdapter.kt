package com.averoes.footballapp.utils

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.model.club.TeamsItem
import com.averoes.footballapp.mvp.view.activity.TeamDetail
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_club.view.*
import org.jetbrains.anko.intentFor

class TeamAdapter(private val item:List<TeamsItem>) :RecyclerView.Adapter<TeamAdapter.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view){

        private val clubBadge:ImageView = view.club_badge_item
        private val clubName:TextView = view.club_name_item

        fun bind(item:TeamsItem){

            Glide.with(itemView.context).load(item.clubBadge).into(clubBadge)
            clubName.text = item.teamName

            itemView.setOnClickListener {
                itemView.context.startActivity(itemView.context.intentFor<TeamDetail>("team_detail" to item))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder =

        Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_club, parent, false))



    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.bind(item[position])
    }
}