package com.averoes.footballapp.mvp.view.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.model.FavoriteTeam
import com.averoes.footballapp.mvp.model.club.TeamsItem
import com.averoes.footballapp.mvp.model.db.database
import com.averoes.footballapp.mvp.model.event.EventsItem
import com.averoes.footballapp.mvp.model.league.LeaguesItem
import com.averoes.footballapp.mvp.presenter.MatchDetailPresenter
import com.averoes.footballapp.mvp.view.MatchDetailView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.activity_team_detail.btn_favorite_team
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class TeamDetail : AppCompatActivity(), MatchDetailView {

    private lateinit var presenter: MatchDetailPresenter
    private lateinit var teamsItem: TeamsItem
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)


        presenter = MatchDetailPresenter(this)

        teamsItem = TeamsItem()

        val detailTeam = intent?.getParcelableExtra<TeamsItem>("team_detail")
        presenter.getTeamDetail(detailTeam?.idTeam.toString())
        btn_favorite_team.setOnClickListener {
            favoteState(teamsItem.idTeam.toString())

            if (isFavorite) removeFromFavorite(detailTeam?.idTeam.toString()) else addToFavorite()

        }

    }

    override fun showLoading() {

    }

    override fun hideLoading() {
    }


    override fun showTeamDetail(data: List<TeamsItem>?, isHome: Boolean) {

        Glide.with(applicationContext).load(data!![0].clubBadge).into(club_badge_detail)
        Glide.with(applicationContext).load(data[0].teamBanner).into(club_banner)

        club_name_detail.text = data[0].teamName
        club_desc_detail.text = data[0].teamDescription

        teamsItem = TeamsItem(
            data[0].idTeam,
            data[0].teamName,
            data[0].clubBadge,
            data[0].teamFormedYear,
            data[0].teamStadium,
            data[0].teamDescription,
            data[0].strSport,
            data[0].teamBanner)
    }

    override fun showDetailEvent(data: List<EventsItem>?) {
    }

    override fun showDetailLeague(data: List<LeaguesItem>?) {
    }

    private fun addToFavorite(){

        if (!TextUtils.isEmpty(club_name_detail.text)){

            try {
                database.use {
                    insert(
                        FavoriteTeam.TABLE_TEAM,
                        FavoriteTeam.TEAM_ID to teamsItem.idTeam,
                        FavoriteTeam.TEAM_NAME to teamsItem.teamName,
                        FavoriteTeam.TEAM_DESC to teamsItem.teamDescription,
                        FavoriteTeam.TEAM_BANNER to teamsItem.teamBanner,
                        FavoriteTeam.TEAM_POSTER to teamsItem.clubBadge)

                }

                toast("Add to Favorite")

            }catch (e : SQLiteConstraintException){
                toast(e.localizedMessage)
            }
        }else{
            toast("Please Try Again")
        }
    }

    private fun removeFromFavorite(id: String) {
        try {
            database.use {

                delete(FavoriteTeam.TABLE_TEAM, "(TEAM_ID = {idTeam})", "idTeam" to id)
            }

            toast("Removed From Favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun favoteState(id: String) {
        database.use {
            val result = select(FavoriteTeam.TABLE_TEAM).whereArgs("(TEAM_ID = {idTeam})", "idTeam" to id)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (favorite.isNotEmpty()) isFavorite = true

        }
    }
}

