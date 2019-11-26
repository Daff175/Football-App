package com.averoes.footballapp.mvp.view.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.model.Favorite
import com.averoes.footballapp.mvp.model.club.TeamsItem
import com.averoes.footballapp.mvp.model.db.database
import com.averoes.footballapp.mvp.model.event.EventsItem
import com.averoes.footballapp.mvp.presenter.TeamDetailPresenter
import com.averoes.footballapp.mvp.view.TeamDetailView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class TeamDetail : AppCompatActivity(), TeamDetailView {
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teams: TeamsItem
    private lateinit var event: EventsItem

    private var menuItem: Menu? = null


    private var isFavorite: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detail = intent.getParcelableExtra<EventsItem>("detail")
        presenter = TeamDetailPresenter(this)

        presenter.getEventDetail(detail.idEvent.toString())
        presenter.getTeamDetail(detail.idHomeTeam.toString())
        presenter.getTeamDetail(detail.idAwayTeam.toString(), false)
        favoteState(detail.idEvent.toString())


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        menuItem = menu
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val detail = intent.getParcelableExtra<EventsItem>("detail")

        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite(detail.idEvent.toString()) else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //add to database


    private fun addToFavorite() {

        if (!TextUtils.isEmpty(name_awayDetail.text)) {


            try {
                database.use {
                    insert(
                        Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to event.idEvent,
                        Favorite.EVENT_ID to event.idEvent,
                        Favorite.EVENT_NAME to event.dateEvent,
                        Favorite.EVENT_DATE to event.dateEvent,
                        Favorite.HOME_TEAM_ID to event.idHomeTeam,
                        Favorite.HOME_TEAM_NAME to event.strHomeTeam,
                        Favorite.HOME_TEAM_SCORE to event.intHomeScore,
                        Favorite.AWAY_TEAM_ID to event.idAwayTeam,
                        Favorite.AWAY_TEAM_NAME to event.strAwayTeam,
                        Favorite.AWAY_TEAM_SCORE to event.intAwayScore,
                        Favorite.CARD_HOME to event.strHomeYellowCards,
                        Favorite.CARD_AWAY to event.strAwayYellowCards,
                        Favorite.GOAL_HOME to event.strHomeGoalDetails,
                        Favorite.GOAL_AWAY to event.strAwayGoalDetails
                    )
                }
                toast("Added to Favorite")

            } catch (e: SQLiteConstraintException) {
                toast(e.localizedMessage)
            }
        } else {
            toast("Please try again")
            isFavorite = false
            setFavorite()
        }
    }

    private fun removeFromFavorite(id: String) {
        try {
            database.use {

                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})", "id" to id)
            }

            toast("Removed From Favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_fav)
            val bundle = Bundle()
            isFavorite = false
            bundle.putBoolean("state", isFavorite)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_fav)
            isFavorite = true
            val bundle = Bundle()
            bundle.putBoolean("state", isFavorite)
        }

    }

    private fun favoteState(id: String) {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE).whereArgs("(EVENT_ID = {id})", "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showTeamDetail(data: List<TeamsItem>?, isHome: Boolean) {
        teams = TeamsItem(
            data!![0].idTeam,
            data[0].teamName,
            data[0].clubBadge,
            data[0].teamFormedYear,
            data[0].teamStadium,
            data[0].teamDescription
        )


        Glide.with(applicationContext).load(data[0].clubBadge).into(if (isHome) img_home_detail else img_away_detail)


    }

    override fun showDetailEvent(data: List<EventsItem>?) {

        event = EventsItem(
            data!![0].strHomeGoalDetails,
            data[0].idEvent,
            data[0].strHomeYellowCards,
            data[0].idHomeTeam,
            data[0].intHomeScore,
            data[0].dateEvent,
            data[0].strAwayTeam,
            data[0].idAwayTeam,
            data[0].strTime,
            data[0].strAwayGoalDetails,
            data[0].strAwayGoalDetails,
            data[0].strHomeTeam,
            data[0].intAwayScore
        )

        name_homeDetail.text = event.strHomeTeam
        name_awayDetail.text = event.strAwayTeam
        score_homeDetail.text = event.intHomeScore
        score_awayDetail.text = event.intAwayScore
        goal_home.text = event.strHomeGoalDetails
        goal_away.text = event.strAwayGoalDetails

        if (event.strHomeYellowCards != null) yellow_cardHome.text =
            event.strHomeYellowCards else yellow_cardHome.text = "-"
        if (event.strAwayGoalDetails != null) yellow_cardAway.text =
            event.strAwayYellowCards else yellow_cardAway.text = "-"

    }

}
