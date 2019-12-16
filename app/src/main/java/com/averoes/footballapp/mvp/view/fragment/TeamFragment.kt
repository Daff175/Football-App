package com.averoes.footballapp.mvp.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.model.club.TeamsItem
import com.averoes.footballapp.mvp.presenter.TeamPresenter
import com.averoes.footballapp.mvp.view.TeamView
import com.averoes.footballapp.utils.TeamAdapter
import com.example.footballapp.mvp.model.soccer.CountrysItem
import kotlinx.android.synthetic.main.fragment_team.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamFragment : androidx.fragment.app.Fragment(), TeamView {

    private var data: MutableList<TeamsItem> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = TeamPresenter(this)
        adapter = TeamAdapter(data)
        team_list.adapter = adapter
        team_list.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        adapter.notifyDataSetChanged()


        val data = activity?.intent?.getParcelableExtra<CountrysItem>("detail")

        presenter.getAllClub(data?.idLeague!!)




        search_team.queryHint = "Search Team..."
        search_team.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {


                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                val query = search_team.query

                if (query.toString().isEmpty()){
                    presenter.getAllClub(data.idLeague)

                }else{
                    presenter.searchTeam(query.toString())
                }
                adapter.notifyDataSetChanged()

                return false
            }

        })
    }

    override fun showLoading() {

        if (team_loading != null) {
            team_loading.visibility = View.VISIBLE

        }
    }

    override fun hideLoading() {
        if (team_loading != null) {
            team_loading.visibility = View.GONE

        }
    }

    override fun showMessage(pesan: String) {
    }

    override fun errorMessage(error: String) {
    }

    override fun showTeamList(match: List<TeamsItem>?) {

        if (match != null){
            data.clear()
            data.addAll(match)
        }
        adapter.notifyDataSetChanged()
    }

}
