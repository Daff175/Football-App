package com.averoes.footballapp.mvp.model.event

import com.google.gson.annotations.SerializedName

data class ResponseSearch(
    @field:SerializedName("event")
    val event : List<EventsItem>? = null
)