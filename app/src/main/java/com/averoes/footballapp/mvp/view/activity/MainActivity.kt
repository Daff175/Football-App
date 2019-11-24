package com.averoes.footballapp.mvp.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.view.fragment.FavoriteFragment
import com.averoes.footballapp.mvp.view.fragment.MatchFragment
import com.averoes.footballapp.mvp.view.fragment.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->

            when(menuItem.itemId){
                R.id.teams -> {
                    loadMatchFragment(savedInstanceState)
                }
                R.id.favorites -> {
                    loadFavoriteFragment(savedInstanceState)
                }

                R.id.search ->{
                    loadSearchFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.teams
    }

    fun loadMatchFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction().replace(
                    R.id.main_container,
                    MatchFragment())
                .commit()
        }
    }

    fun loadFavoriteFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction().replace(
                    R.id.main_container,
                    FavoriteFragment(),
                    FavoriteFragment::class.java.simpleName)
                .commit()
        }
    }

    fun loadSearchFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction().replace(
                    R.id.main_container,
                    SearchFragment(),
                    SearchFragment::class.java.simpleName)
                .commit()
        }
    }
}
