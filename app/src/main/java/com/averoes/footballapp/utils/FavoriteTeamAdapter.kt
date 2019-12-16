package com.averoes.footballapp.utils

import android.database.sqlite.SQLiteConstraintException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.model.FavoriteTeam
import com.averoes.footballapp.mvp.model.db.database
import com.averoes.footballapp.mvp.view.activity.FavoriteTeamDetail
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_favorite_team.view.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class FavoriteTeamAdapter(private val item: List<FavoriteTeam>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<FavoriteTeamAdapter.Holder>() {


    class Holder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        private val poster: ImageView = view.club_badge_item_favorite
        private val name: TextView = view.club_name_item_favorite
        private val btnUnFavorite = view.btn_unfavorite_team_favorite

        fun bind(favoriteTeam: FavoriteTeam) {

            Glide.with(itemView.context).load(favoriteTeam.poster).into(poster)
            name.text = favoriteTeam.teamName

            btnUnFavorite.setOnClickListener {

                try {
                    itemView.context?.database?.use {

                        delete(FavoriteTeam.TABLE_TEAM, "(ID_ = {idTeam})", "idTeam" to favoriteTeam.idTeam.toString())
                    }
                    itemView.context.toast("removed from favorite")
                } catch (e: SQLiteConstraintException) {
                    itemView.context.toast(e.localizedMessage)
                    e.printStackTrace()
                }

            }

            itemView.setOnClickListener {

                itemView.context.startActivity(itemView.context.intentFor<FavoriteTeamDetail>("detail" to favoriteTeam))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder =

        Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_team, parent, false))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(item[position])
    }
}