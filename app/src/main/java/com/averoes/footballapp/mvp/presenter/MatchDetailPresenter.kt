package com.averoes.footballapp.mvp.presenter

import com.averoes.footballapp.mvp.view.MatchDetailView
import com.averoes.footballapp.networking.InitRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MatchDetailPresenter(val view: MatchDetailView) {

    fun getLeagueDetail(leagueId: String){
        view.showLoading()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val service = InitRetrofit().getInstance()
                val request = service.getDetailLeague(leagueId)
                if (request.isSuccessful){
                    withContext(Dispatchers.Main){
                        view.showDetailLeague(request.body()?.leagues)
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }catch (e:Throwable){
                e.printStackTrace()
            }
        }
    }

    fun getTeamDetail(teamId:String, isHome: Boolean = true){
        view.showLoading()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val service = InitRetrofit().getInstance()
                val request = service.getTeamDetail(teamId)
                if (request.isSuccessful){
                    withContext(Dispatchers.Main){
                        view.showTeamDetail(request.body()?.teams, isHome)

                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }catch (e:Throwable){
                e.printStackTrace()
            }
        }

    }

    fun getEventDetail(id : String){
        view.showLoading()

        GlobalScope.launch(Dispatchers.IO){
            try {
                val service = InitRetrofit().getInstance()
                val response = service.getMatchDetail(id)
                if (response.isSuccessful){
                    withContext(Dispatchers.Main){
                        view.showDetailEvent(response.body()?.events)
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