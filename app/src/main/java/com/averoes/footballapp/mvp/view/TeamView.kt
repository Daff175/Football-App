package com.averoes.footballapp.mvp.view

import com.averoes.footballapp.mvp.model.club.TeamsItem

interface TeamView {

    fun showLoading()
    fun hideLoading()
    fun showMessage(pesan:String)
    fun errorMessage(error:String)
    fun showTeamList(match:List<TeamsItem>?)
}