package com.averoes.footballapp.mvp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.presenter.LeaguePresenter
import com.averoes.footballapp.mvp.view.LeagueView
import com.averoes.footballapp.utils.LeagueAdapter
import com.example.footballapp.mvp.model.soccer.CountrysItem
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(),LeagueView {
    private lateinit var presenter : LeaguePresenter
    private var items: MutableList<CountrysItem> = mutableListOf()
    private lateinit var adapter: LeagueAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        presenter = LeaguePresenter(this)
        presenter.getAllLeague()

        adapter = LeagueAdapter(this, items)

        league_list.adapter = adapter
        league_list.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 2)



    }


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMessage(pesan: String) {
    }

    override fun errorMessage(error: String) {
    }

    override fun showMatchList(match: List<CountrysItem>?) {
        if (match != null){
            items.clear()
            items.addAll(match)
        }
        adapter.notifyDataSetChanged()
    }
}

