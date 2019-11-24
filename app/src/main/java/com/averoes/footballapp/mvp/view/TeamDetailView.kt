package com.averoes.footballapp.mvp.view

import com.averoes.footballapp.mvp.model.club.TeamsItem
import com.averoes.footballapp.mvp.model.event.EventsItem

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<TeamsItem>?, isHome: Boolean)
    fun showDetailEvent(data: List<EventsItem>?)
}