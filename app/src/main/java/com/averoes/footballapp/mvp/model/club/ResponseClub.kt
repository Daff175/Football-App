package com.example.footballapp.mvp.model.club

import com.averoes.footballapp.mvp.model.club.TeamsItem
import com.google.gson.annotations.SerializedName

data class ResponseClub(

	@field:SerializedName("teams")
	val teams: List<TeamsItem>? = null
)