package com.example.footballapp.mvp.model.Event

import com.averoes.footballapp.mvp.model.event.EventsItem
import com.google.gson.annotations.SerializedName

data class ResponseEvent(

	@field:SerializedName("events")
	val events: List<EventsItem>? = null
)