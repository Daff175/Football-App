package com.averoes.footballapp.mvp.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.SearchView
import com.averoes.footballapp.R
import com.averoes.footballapp.TeamAdapter
import com.averoes.footballapp.mvp.model.event.EventsItem
import com.averoes.footballapp.mvp.presenter.MatchPresenter
import com.averoes.footballapp.mvp.view.MatchView
import kotlinx.android.synthetic.main.fragment_search.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchFragment : Fragment(), MatchView {
    private lateinit var adapter: TeamAdapter

    private lateinit var presenter: MatchPresenter
    private var event = mutableListOf<EventsItem>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.actionBar?.title = "Search Match"

        presenter = MatchPresenter(this)
        adapter = TeamAdapter(activity!!.baseContext, event)

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
        loading_search.visibility = VISIBLE
    }

    override fun hideLoading() {
        loading_search.visibility = GONE
    }

    override fun showMessage(pesan: String) {
    }

    override fun errorMessage(error: String) {
        toast(error)
    }

    override fun showMatchList(match: List<EventsItem>?) {
        if (match != null){
            event.clear()
            event.addAll(match)
        }
        adapter.notifyDataSetChanged()
    }


}
