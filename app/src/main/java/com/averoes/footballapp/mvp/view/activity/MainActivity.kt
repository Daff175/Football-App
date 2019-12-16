package com.averoes.footballapp.mvp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.averoes.footballapp.R
import com.averoes.footballapp.mvp.view.fragment.HomeFavoriteFragment
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
                    HomeFragment())
                .commit()
        }
    }

    fun loadFavoriteFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction().replace(
                    R.id.main_container,
                    HomeFavoriteFragment(),
                    HomeFavoriteFragment::class.java.simpleName)
                .commit()
        }
    }


}
