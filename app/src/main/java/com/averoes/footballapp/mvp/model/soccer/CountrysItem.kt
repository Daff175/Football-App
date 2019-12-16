package com.example.footballapp.mvp.model.soccer

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountrysItem(

	@SerializedName("idLeague")
	val idLeague:String? = null,

	@SerializedName("strLeague")
	val leagueName:String? = null,

	@SerializedName("strDescriptionEN")
	val leagueDesc:String? = null,

	@SerializedName("strBadge")
	val leagueBadge:String? = null


):Parcelable