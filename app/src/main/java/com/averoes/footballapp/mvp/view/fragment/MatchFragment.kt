package com.averoes.footballapp.mvp.view.fragment


import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.view.activity.SearchActivity
import kotlinx.android.synthetic.main.fragment_match.view.*
import org.jetbrains.anko.support.v4.intentFor

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchFragment : androidx.fragment.app.Fragment() {
    private lateinit var viewPager: androidx.viewpager.widget.ViewPager
    private lateinit var tabs: TabLayout
    private lateinit var btnSearch: FloatingActionButton
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_match, container, false)

        viewPager = view.findViewById(R.id.view_pager)
        tabs = view.findViewById(R.id.tablayout)
        btnSearch = view.findViewById(R.id.btn_search)

        val adapter = TabAdapter(childFragmentManager)
        view.view_pager.adapter = adapter

        view.tablayout.setupWithViewPager(viewPager)

        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnSearch.setOnClickListener {

            startActivity(intentFor<SearchActivity>())
        }
    }

}

private class TabAdapter(supportFragmentManager: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(supportFragmentManager) {


    override fun getItem(position: Int): androidx.fragment.app.Fragment =
        when (position) {
            0 -> LastFragment()

            else -> NextFragment()
        }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Last Match"

            else -> {
                return "Next Match"
            }
        }
    }


}
