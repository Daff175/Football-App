package com.averoes.footballapp.mvp.view

import com.example.footballapp.mvp.model.soccer.CountrysItem

interface LeagueView {

    fun showLoading()
    fun hideLoading()
    fun showMessage(pesan:String)
    fun errorMessage(error:String)
    fun showMatchList(match:List<CountrysItem>?)
}