package com.averoes.footballapp.networking

import com.averoes.footballapp.mvp.model.event.ResponseSearch
import com.example.footballapp.mvp.model.Event.ResponseEvent
import com.example.footballapp.mvp.model.club.ResponseClub
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("search_all_teams.php")
    fun getAllClub(@Query("l")league : String):Call<ResponseClub>

    @GET("lookupteam.php")
    suspend fun getTeamDetail(@Query("id") teamId:String): Response<ResponseClub>

    @GET("eventspastleague.php")
    suspend fun getLastMatch(@Query("id")id : String) : Response<ResponseEvent>

    @GET("eventsnextleague.php")
    suspend fun getNextMatch(@Query("id")id : String) : Response<ResponseEvent>

    @GET("lookupevent.php")
    suspend fun getMatchDetail(@Query("id")id : String) : Response<ResponseEvent>

    @GET("searchevents.php")
    suspend fun searchEvent(@Query("e")event:String):Response<ResponseSearch>

}