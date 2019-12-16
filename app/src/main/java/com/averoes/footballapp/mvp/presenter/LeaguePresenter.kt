package com.averoes.footballapp.mvp.presenter

import com.averoes.footballapp.mvp.view.LeagueView
import com.averoes.footballapp.networking.InitRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LeaguePresenter (private val view: LeagueView){

    fun getAllLeague(){
        view.showLoading()

        GlobalScope.launch (Dispatchers.IO){
            try {
                val api = InitRetrofit().getInstance()
                val response = api.getAllLeague()
                if (response.isSuccessful){
                    withContext(Dispatchers.Main){
                        view.hideLoading()
                        view.showMatchList(response.body()?.countrys?.filter { it.leagueBadge != null })

                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }catch (t:Throwable){
                t.printStackTrace()
            }
        }
    }
}