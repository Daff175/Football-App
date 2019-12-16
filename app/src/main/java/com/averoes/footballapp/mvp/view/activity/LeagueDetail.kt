package com.averoes.footballapp.mvp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.model.ItemCLub
import com.averoes.footballapp.mvp.model.club.TeamsItem
import com.averoes.footballapp.mvp.model.event.EventsItem
import com.averoes.footballapp.mvp.model.league.LeaguesItem
import com.averoes.footballapp.mvp.presenter.MatchDetailPresenter
import com.averoes.footballapp.mvp.view.MatchDetailView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_league_detail.*

class LeagueDetail : AppCompatActivity(), MatchDetailView {

    private lateinit var presenter:MatchDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)

        val detail = intent.getParcelableExtra<ItemCLub>("detail")

        presenter = MatchDetailPresenter(this)
        presenter.getLeagueDetail(detail.id)





    }


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showTeamDetail(data: List<TeamsItem>?, isHome: Boolean) {
    }

    override fun showDetailEvent(data: List<EventsItem>?) {
    }

    override fun showDetailLeague(data: List<LeaguesItem>?) {

        Glide.with(this).load(data?.get(0)?.strFanart4).into(banner_league)
        Glide.with(this).load(data?.get(0)?.strLogo).into(poster_league)

        name_league.text = data?.get(0)?.strLeague
        desc_league.text = data?.get(0)?.strDescriptionEN

        supportActionBar?.title = data?.get(0)?.strLeague


    }
}
