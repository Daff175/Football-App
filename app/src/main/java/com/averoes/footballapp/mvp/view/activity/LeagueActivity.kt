package com.averoes.footballapp.mvp.view.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.view.fragment.MatchFragment
import com.averoes.footballapp.mvp.view.fragment.TableFragment
import com.averoes.footballapp.mvp.view.fragment.TeamFragment
import com.bumptech.glide.Glide
import com.example.footballapp.mvp.model.soccer.CountrysItem
import kotlinx.android.synthetic.main.activity_league.*

class LeagueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)

        setSupportActionBar(toolbar)


        val detail = intent.getParcelableExtra<CountrysItem>("detail")

        supportActionBar?.title = detail.leagueName

        desc_league.text = detail.leagueDesc


        collapsing_toolbar.setCollapsedTitleTextColor(
            ContextCompat.getColor(this, android.R.color.white)
        )



        Glide.with(this).load(detail.leagueBadge).into(backdrop)

        val adapter = TabAdapter(supportFragmentManager)
        viewpager.adapter = adapter
        tablayout.setupWithViewPager(viewpager)

    }

    private class TabAdapter(supportFragmentManager: FragmentManager) :
        androidx.fragment.app.FragmentStatePagerAdapter(supportFragmentManager) {


        override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> MatchFragment()

                1 -> TeamFragment()

                else -> TableFragment()
            }

        override fun getCount(): Int = 3

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "Match"

                1 -> "Teams"

                else -> "Standings"
            }
        }

    }
}
