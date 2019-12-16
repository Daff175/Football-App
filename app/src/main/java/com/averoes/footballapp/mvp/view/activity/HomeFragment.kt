package com.averoes.footballapp.mvp.view.activity


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.presenter.LeaguePresenter
import com.averoes.footballapp.mvp.view.LeagueView
import com.averoes.footballapp.utils.LeagueAdapter
import com.example.footballapp.mvp.model.soccer.CountrysItem
import kotlinx.android.synthetic.main.activity_home.league_list
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : androidx.fragment.app.Fragment(), LeagueView {

    private lateinit var presenter: LeaguePresenter
    private var items: MutableList<CountrysItem> = mutableListOf()
    private lateinit var adapter: LeagueAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        presenter = LeaguePresenter(this)
        adapter = LeagueAdapter(activity!!.baseContext, items)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter.getAllLeague()
        league_list.adapter = adapter
        league_list.layoutManager =
            androidx.recyclerview.widget.GridLayoutManager(activity!!.baseContext, 2)

    }

    override fun showLoading() {
        if (loading_league != null) {
            loading_league.visibility = VISIBLE
        }
    }

    override fun hideLoading() {
        if (loading_league != null) {
            loading_league.visibility = GONE
        }
    }

    override fun showMessage(pesan: String) {
    }

    override fun errorMessage(error: String) {
    }

    override fun showMatchList(match: List<CountrysItem>?) {

        if (match != null) {
            items.clear()
            items.addAll(match)
        }
        adapter.notifyDataSetChanged()
    }
}


