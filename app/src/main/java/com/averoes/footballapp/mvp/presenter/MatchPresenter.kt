package com.averoes.footballapp.mvp.presenter

import android.util.Log
import com.averoes.footballapp.mvp.model.event.ResponseSearch
import com.averoes.footballapp.mvp.view.MatchView
import com.averoes.footballapp.networking.InitRetrofit
import com.example.footballapp.mvp.model.Event.ResponseEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchPresenter(private val view: MatchView) {

    fun getNextMatch(leagueId:Int){
        view.showLoading()
        val api = InitRetrofit().getInstance()
        api.getNextMatch(leagueId.toString()).enqueue(object : Callback<ResponseEvent> {
            override fun onFailure(call: Call<ResponseEvent>, t: Throwable) {
                view.hideLoading()
                view.errorMessage("Network Failure")
                Log.d("RESPONSE", "ERROR")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ResponseEvent>, response: Response<ResponseEvent>) {

                view.hideLoading()
                val teamList = response.body()?.events
                view.showMatchList(teamList)
                view.showMessage("Succes")
                Log.d("RESPONSE","SUCCES" )

            }

        })
    }

    fun getLastMatch(leagueId:Int){
        view.showLoading()
        val api = InitRetrofit().getInstance()
        api.getLastMatch(leagueId.toString()).enqueue(object : Callback<ResponseEvent> {
            override fun onFailure(call: Call<ResponseEvent>, t: Throwable) {
                view.hideLoading()
                view.errorMessage("Network Failure")
                Log.d("RESPONSE", "ERROR")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ResponseEvent>, response: Response<ResponseEvent>) {

                view.hideLoading()
                val teamList = response.body()?.events
                view.showMatchList(teamList)
                view.showMessage("Succes")
                Log.d("RESPONSE","SUCCES" )

            }

        })
    }

    fun searchMatch(event:String){
        view.showLoading()
        InitRetrofit().getInstance().searchEvent(event)
            .enqueue(object : Callback<ResponseSearch>{
                override fun onFailure(call: Call<ResponseSearch>, t: Throwable) {
                    view.errorMessage("error")
                    t.printStackTrace()
                    Log.e("RESPONSE", "ERROR")

                }

                override fun onResponse(call: Call<ResponseSearch>, response: Response<ResponseSearch>) {

                    view.hideLoading()
                    val event = response.body()?.event?.filter { it.strSport == "Soccer" }
                    view.showMatchList(event)
                    view.showMessage("Success")
                    Log.d("RESPONSE","SUCCES" )

                }

            })
    }

}