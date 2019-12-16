package com.averoes.footballapp.mvp.view

import com.averoes.footballapp.mvp.model.club.TeamsItem
import com.averoes.footballapp.mvp.model.event.EventsItem
import com.averoes.footballapp.mvp.model.league.LeaguesItem

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<TeamsItem>?, isHome: Boolean)
    fun showDetailEvent(data: List<EventsItem>?)
    fun showDetailLeague(data: List<LeaguesItem>?)
}