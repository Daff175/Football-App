package com.averoes.footballapp.mvp.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.averoes.footballapp.R
import com.averoes.footballapp.TeamAdapter
import com.averoes.footballapp.mvp.model.event.EventsItem
import com.averoes.footballapp.mvp.presenter.MatchPresenter
import com.averoes.footballapp.mvp.view.MatchView
import kotlinx.android.synthetic.main.fragment_last.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 *
 */
class LastFragment : Fragment(), MatchView {

    private var data: MutableList<EventsItem> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: TeamAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_last, container, false)
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = MatchPresenter(this)
        adapter = TeamAdapter(activity!!.applicationContext,data)
        last_match.adapter = adapter
        last_match.layoutManager = LinearLayoutManager(ctx)


        val spinerItem = resources.getStringArray(R.array.league)
        val idLeague = resources.getStringArray(R.array.idLeague)
        val spinnerLeague = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinerItem)
        spinner.adapter = spinnerLeague


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                presenter.getLastMatch(idLeague[position].toInt())

            }

        }

    }

    override fun showLoading() {

        if (loading_last != null){
            loading_last.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        if (loading_last != null){
            loading_last.visibility = View.GONE
        }
    }

    override fun showMatchList(match: List<EventsItem>?) {
        data.clear()
        if (match != null){
            data.addAll(match)
        }else{
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
