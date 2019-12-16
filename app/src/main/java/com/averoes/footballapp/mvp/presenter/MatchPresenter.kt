package com.averoes.footballapp.mvp.presenter

import com.averoes.footballapp.mvp.view.MatchView
import com.averoes.footballapp.networking.InitRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MatchPresenter(private val view: MatchView) {

    fun getNextMatch(leagueId:Int){
        view.showLoading()

        GlobalScope.launch (Dispatchers.IO){
            try {
                val api = InitRetrofit().getInstance()
                val response = api.getNextMatch(leagueId.toString())
                if (response.isSuccessful){
                    withContext(Dispatchers.Main){
                        view.hideLoading()
                        view.showMatchList(response.body()?.events)
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }catch (e:Throwable){
                e.printStackTrace()
            }
        }

    }

    fun getLastMatch(leagueId:Int){
        view.showLoading()

        GlobalScope.launch (Dispatchers.IO){
            try {
                val api = InitRetrofit().getInstance()
                val response = api.getLastMatch(leagueId.toString())
                if (response.isSuccessful){
                    withContext(Dispatchers.Main){
                        view.hideLoading()
                        view.showMatchList(response.body()?.events)
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }catch (e:Throwable){
                e.printStackTrace()
            }
        }

    }

    fun searchMatch(event:String){
        view.showLoading()

        GlobalScope.launch (Dispatchers.IO){
            try {
                val api = InitRetrofit().getInstance()
                val response = api.searchEvent(event)
                if (response.isSuccessful){
                    withContext(Dispatchers.Main){
                        view.hideLoading()
                        val leagueList= response.body()?.event
                        view.showMatchList(leagueList?.filter { it.strSport == "Soccer" })
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }catch (e:Throwable){
                e.printStackTrace()
            }
        }
    }

}