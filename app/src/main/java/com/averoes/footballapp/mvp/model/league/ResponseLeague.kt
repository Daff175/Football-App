package com.averoes.footballapp.mvp.model.league

import com.google.gson.annotations.SerializedName

data class ResponseLeague(

	@field:SerializedName("leagues")
	val leagues: List<LeaguesItem>? = null
)