package com.averoes.footballapp.mvp.model.club

import com.google.gson.annotations.SerializedName

data class TeamsItem(

	@SerializedName("idTeam")
	val idTeam:String? = null,

	@SerializedName("strTeam")
	val teamName:String? = null,

	@SerializedName("strTeamBadge")
	val clubBadge:String? = null,

	@SerializedName("intFormedYear")
	var teamFormedYear: String? = null,

	@SerializedName("strStadium")
	var teamStadium: String? = null,

	@SerializedName("strDescriptionEN")
	var teamDescription: String? = null
)