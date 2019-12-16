package com.averoes.footballapp.mvp.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.averoes.footballapp.R
import kotlinx.android.synthetic.main.fragment_home_favorite.*
import kotlinx.android.synthetic.main.fragment_home_favorite.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_favorite, container, false)

        view.tablayout.setupWithViewPager(view_pager)

        val adapter = FavoriteTabAdapter(childFragmentManager).apply {
            addFragment(FavoriteMatchFragment().apply { retainInstance = true }, getString(R.string.match))
            addFragment(FavoriteTeamFragment().apply { retainInstance = true }, getString(R.string.tim))

        }

        view.view_pager.adapter = adapter

        return view
    }


}

class FavoriteTabAdapter(supportFragmentManager: FragmentManager) : FragmentStatePagerAdapter(supportFragmentManager) {

    private val fragmentList = arrayListOf<Fragment>()
    private val fragmentTitleList = arrayListOf<String>()

    override fun getItem(position: Int): Fragment =
        fragmentList[position]

    override fun getCount(): Int = fragmentList.size

    override fun getPageTitle(position: Int): CharSequence? =
        fragmentTitleList[position]

    fun addFragment(fragment: Fragment, title:String){

        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }
}
