package com.averoes.footballapp.mvp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.model.Favorite
import kotlinx.android.synthetic.main.activity_favorite_detail.*

class FavoriteMatchDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_detail)

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val data = intent.getParcelableExtra<Favorite>("favorite")

        name_homeDetail.text = data.homeTeam
        name_awayDetail.text = data.awayTeam
        score_homeDetail.text = data.homeScore
        score_awayDetail.text = data.awayScore
        goal_home.text = data.goalHome
        goal_away.text = data.goalAway
        yellow_cardHome.text = data.cardHome
        yellow_cardaway.text = data.cardAway
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
