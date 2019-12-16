package com.averoes.footballapp.mvp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.SearchView
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.model.event.EventsItem
import com.averoes.footballapp.mvp.presenter.MatchPresenter
import com.averoes.footballapp.mvp.view.MatchView
import com.averoes.footballapp.utils.MatchAdapter
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.ctx

class SearchActivity : AppCompatActivity(), MatchView {

    private lateinit var adapter: MatchAdapter

    private lateinit var presenter: MatchPresenter
    private var event = mutableListOf<EventsItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar?.title = "Search Match..."

        presenter = MatchPresenter(this)
        adapter = MatchAdapter(baseContext, event)

        result_search.layoutManager = LinearLayoutManager(ctx)
        result_search.adapter = adapter
        adapter.notifyDataSetChanged()

        sear_match.queryHint = "Search Match..."
        sear_match.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.searchMatch(sear_match.query.toString())

                return false
            }

        })

    }

    override fun showLoading() {
        if (result_search == null){
            loading_search.visibility = VISIBLE
        }
    }

    override fun hideLoading() {
        if (result_search != null){
            loading_search.visibility = GONE
        }
    }

    override fun showMessage(pesan: String) {
    }

    override fun errorMessage(error: String) {
    }

    override fun showMatchList(match: List<EventsItem>?) {

        if (match != null){
            event.clear()
            event.addAll(match)
        }
        adapter.notifyDataSetChanged()
    }

}