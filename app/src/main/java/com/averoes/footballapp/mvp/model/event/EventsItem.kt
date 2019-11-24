package com.averoes.footballapp.mvp.model.event

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventsItem(

	@field:SerializedName("strHomeGoalDetails")
	val strHomeGoalDetails: String? = null,

	@field:SerializedName("idEvent")
	val idEvent: String? = null,

	@field:SerializedName("strHomeYellowCards")
	val strHomeYellowCards: String? = null,

	@field:SerializedName("idHomeTeam")
	val idHomeTeam: String? = null,

	@field:SerializedName("intHomeScore")
	val intHomeScore: String? = null,

	@field:SerializedName("dateEvent")
	val dateEvent: String? = null,

	@field:SerializedName("strAwayTeam")
	val strAwayTeam: String? = null,

	@field:SerializedName("idAwayTeam")
	val idAwayTeam: String? = null,

	val strTime: String? = null,

	@field:SerializedName("strAwayGoalDetails")
	val strAwayGoalDetails: String? = null,

	@field:SerializedName("strAwayYellowCards")
	val strAwayYellowCards: String? = null,

	@field:SerializedName("strHomeTeam")
	val strHomeTeam: String? = null,

	@field:SerializedName("intAwayScore")
	val intAwayScore: String? = null,

	@field:SerializedName("strSport")
	val strSport: String? = null

):Parcelable