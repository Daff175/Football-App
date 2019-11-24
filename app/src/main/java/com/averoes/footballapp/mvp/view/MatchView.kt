package com.averoes.footballapp.mvp.view

import com.averoes.footballapp.mvp.model.event.EventsItem

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMessage(pesan:String)
    fun errorMessage(error:String)
    fun showMatchList(match:List<EventsItem>?)
}