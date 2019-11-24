package com.averoes.footballapp

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.averoes.footballapp.mvp.model.Favorite
import com.averoes.footballapp.mvp.model.db.database
import com.averoes.footballapp.mvp.view.activity.FavoriteDetail
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.find
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class FavoriteTeamAdapter(private val favorite: List<Favorite>) : RecyclerView.Adapter<FavoriteTeamAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Holder =
        Holder(LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false))

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindItem(favorite[position])
    }


    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamName = itemView.find<TextView>(R.id.home_team)
        private val teamAway = itemView.find<TextView>(R.id.away_team)
        private val homeScore = itemView.find<TextView>(R.id.home_team_score)
        private val awayScore = itemView.find<TextView>(R.id.away_team_score)
        private val removeFav = itemView.find<ImageView>(R.id.remove_fav)

        fun bindItem(favorite: Favorite) {
            if (favorite.homeTeam != null) teamName.text = favorite.homeTeam else teamName.text = "-"
            if (favorite.awayTeam != null) teamAway.text = favorite.awayTeam else teamAway.text = "-"
            if (favorite.homeScore != null) homeScore.text = favorite.homeScore else homeScore.text = "-"
            if (favorite.awayScore != null) awayScore.text = favorite.awayScore else awayScore.text = "-"



            removeFav.setOnClickListener {

                try {
                    itemView.context.database.use {

                        delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})", "id" to favorite.eventId.toString())

                    }

                    itemView.context.toast("Removed From Favorite")
                } catch (e: SQLiteConstraintException) {
                    itemView.context.toast(e.localizedMessage)
                }
            }

            itemView.setOnClickListener {
                itemView.context.startActivity(itemView.context.intentFor<FavoriteDetail>(
                    "favorite" to favorite
                ))
            }


        }
    }

}