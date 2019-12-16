package com.averoes.footballapp.mvp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.model.FavoriteTeam
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_favorite_team_detail.*

class FavoriteTeamDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_team_detail)

        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detail = intent.getParcelableExtra<FavoriteTeam>("detail")

        club_name_detail.text = detail.teamName
        club_desc_detail.text = detail.teamDesc
        Glide.with(this).load(detail.banner).into(club_banner)
        Glide.with(this).load(detail.poster).into(club_badge_detail)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
