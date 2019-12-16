package com.averoes.footballapp.mvp.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.model.event.EventsItem
import com.averoes.footballapp.mvp.presenter.MatchPresenter
import com.averoes.footballapp.mvp.view.MatchView
import com.averoes.footballapp.utils.MatchAdapter
import com.example.footballapp.mvp.model.soccer.CountrysItem
import kotlinx.android.synthetic.main.fragment_last.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 *
 */
class LastFragment : androidx.fragment.app.Fragment(), MatchView {

    private var data: MutableList<EventsItem> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_last, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = MatchPresenter(this)
        adapter = MatchAdapter(requireContext(), data)
        last_match.adapter = adapter
        last_match.layoutManager = LinearLayoutManager(ctx)


        val data = activity?.intent?.getParcelableExtra<CountrysItem>("detail")

        presenter.getLastMatch(data?.idLeague!!.toInt())

    }

    override fun showLoading() {

        if (loading_last != null) {
            loading_last.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        if (loading_last != null) {
            loading_last.visibility = View.GONE
        }
    }

    override fun showMatchList(match: List<EventsItem>?) {
        data.clear()
        if (match != null) {
            data.addAll(match)
        } else {
            toast("Nothing Data")
        }
        adapter.notifyDataSetChanged()
    }

    override fun showMessage(pesan: String) {
//        toast(pesan)
    }

    override fun errorMessage(error: String) {
//        toast(error)
    }
}
