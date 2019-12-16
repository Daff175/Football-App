package com.averoes.footballapp.mvp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemCLub(val id : String, val name:String) : Parcelable