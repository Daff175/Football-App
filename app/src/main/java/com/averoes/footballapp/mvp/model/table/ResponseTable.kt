package com.averoes.footballapp.mvp.model.table

import com.google.gson.annotations.SerializedName

data class ResponseTable(

	@field:SerializedName("table")
	val table: List<TableItem>? = null
)