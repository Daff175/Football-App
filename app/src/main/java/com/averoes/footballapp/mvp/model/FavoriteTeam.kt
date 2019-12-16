package com.averoes.footballapp.mvp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class FavoriteTeam(val idTeam: Long?,
                   var teamId:String?,
                   var teamName:String?,
                   var teamDesc:String?,
                   var banner:String?,
                   var poster:String?): Parcelable


{
    companion   object{
        const val TABLE_TEAM = "TABLE_TEAM"
        const val ID = "ID_"
        const val TEAM_ID = "TEAM_ID"
        const val TEAM_NAME = "TEAM_NAME"
        const val TEAM_DESC = "TEAM_DESC"
        const val TEAM_BANNER = "TEAM_BANNER"
        const val TEAM_POSTER = "TEAM_POSTER"

    }
}