package com.averoes.footballapp.mvp.presenter

import com.averoes.footballapp.mvp.view.TeamView
import com.averoes.footballapp.networking.InitRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamPresenter(private val view: TeamView) {

    fun getAllClub(id: String) {

        view.showLoading()

        GlobalScope.launch(Dispatchers.IO) {

            try {
                val api = InitRetrofit().getInstance()
                val response = api.getAllTeam(id)
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        view.hideLoading()
                        view.showTeamList(response.body()?.teams)

                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    fun searchTeam(name:String){
        view.showLoading()

        GlobalScope.launch(Dispatchers.IO) {

            try {

                val api = InitRetrofit().getInstance()
                val response = api.searchTeams(name)
                if (response.isSuccessful){
                    withContext(Dispatchers.Main){
                        view.hideLoading()
                        view.showTeamList(response.body()?.teams?.filter { it.strSport == "Soccer" })
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