package com.example.footballapp.mvp.model.soccer

import com.google.gson.annotations.SerializedName

data class ResponseSoccer(

	@field:SerializedName("countrys")
	val countrys: List<CountrysItem>? = null
)