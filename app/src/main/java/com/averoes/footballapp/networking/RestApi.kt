package com.averoes.footballapp.networking

import com.averoes.footballapp.mvp.model.event.ResponseSearch
import com.averoes.footballapp.mvp.model.league.ResponseLeague
import com.averoes.footballapp.mvp.model.table.ResponseTable
import com.example.footballapp.mvp.model.Event.ResponseEvent
import com.example.footballapp.mvp.model.club.ResponseClub
import com.example.footballapp.mvp.model.soccer.ResponseSoccer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("search_all_leagues.php?s=Soccer")
    suspend fun getAllLeague(): Response<ResponseSoccer>

    @GET("lookupleague.php")
    suspend fun getDetailLeague(@Query("id")league : String):Response<ResponseLeague>

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

    @GET("lookuptable.php?s=1819&")
    suspend fun getTableTeam(@Query("l") league: String):Response<ResponseTable>

    @GET("lookup_all_teams.php")
    suspend fun getAllTeam(@Query("id") idLeague: String):Response<ResponseClub>

    @GET("searchteams.php")
    suspend fun searchTeams(@Query("t")name:String):Response<ResponseClub>
    
     @GET("searchteams.php")
    suspend fun mencariTeams(@Query("t")name:String):Response<ResponseClub>

}
