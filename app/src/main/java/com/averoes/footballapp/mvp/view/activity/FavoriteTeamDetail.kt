package com.averoes.footballapp.mvp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.model.FavoriteTeam

class FavoriteTeamDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_team_detail)

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detail = intent.getParcelableExtra<FavoriteTeam>("detail")


    }
}
