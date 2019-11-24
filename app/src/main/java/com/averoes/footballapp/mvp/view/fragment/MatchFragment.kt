package com.averoes.footballapp.mvp.view.fragment


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.averoes.footballapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchFragment : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_match, container, false)

        viewPager = view.findViewById(R.id.view_pager)
        tabs = view.findViewById(R.id.tablayout)

        val adapter = TabAdapter(childFragmentManager)
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
        return view


    }


}
class TabAdapter(supportFragmentManager: FragmentManager): FragmentStatePagerAdapter(supportFragmentManager){


    override fun getItem(position: Int): Fragment =
        when(position){
            0 -> LastFragment()

            else -> NextFragment()
        }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Last Match"

            else -> {
                return "Next Match"
            }
        }
    }


}
