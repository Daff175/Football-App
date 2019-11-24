package com.averoes.footballapp.mvp.presenter

import android.util.Log
import com.averoes.footballapp.mvp.view.TeamDetailView
import com.averoes.footballapp.networking.InitRetrofit
import com.example.footballapp.mvp.model.Event.ResponseEvent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

class TeamDetailPresnter(val view: TeamDetailView) {

    fun getTeamDetail(teamId:String, isHome: Boolean = true){
        view.showLoading()
        val service = InitRetrofit().getInstance()
        GlobalScope.launch {
            val request = service.getTeamDetail(teamId)
            val response = request.awaitResponse()
            try {
                if (response.isSuccessful){
                    view.showTeamDetail(response.body()?.teams, isHome)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }catch (e:Throwable){
                e.printStackTrace()
            }
        }

//            .enqueue(object :Callback<ResponseClub>{
//                override fun onFailure(call: Call<ResponseClub>, t: Throwable) {
//                    Log.d("RESPONSE", "ERROR")
//                    t.printStackTrace()
//                }
//
//                override fun onResponse(call: Call<ResponseClub>, response: Response<ResponseClub>) {
//                    val team = response.body()?.teams
//                    if (response.isSuccessful){
//                        view.hideLoading()
//                        view.showTeamDetail(team, isHome)
//                    }else{
//                        view.hideLoading()
//                        Log.d("RESPONSE", "FAILED TO FETCH DATA")
//                    }
//                }
//
//            })
    }

    fun getEventDetail(id : String){
        view.showLoading()
        InitRetrofit().getInstance().getMatchDetail(id)
            .enqueue(object : Callback<ResponseEvent>{
                override fun onFailure(call: Call<ResponseEvent>, t: Throwable) {
                    Log.d("RESPONSE", "ERROR")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ResponseEvent>, response: Response<ResponseEvent>) {

                    val data = response.body()?.events
                    view.showDetailEvent(data)
                }

            })
    }
}