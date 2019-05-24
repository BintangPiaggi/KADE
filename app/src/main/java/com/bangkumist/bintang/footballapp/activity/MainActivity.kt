package com.bangkumist.bintang.footballapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bangkumist.bintang.footballapp.R

import com.bangkumist.bintang.footballapp.fragment.FavoriteFragment
import com.bangkumist.bintang.footballapp.fragment.LeagueFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_league -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content, LeagueFragment(), LeagueFragment::class.java.simpleName)
                        .commit()
                }
                R.id.favorite_match -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                        .commit()
                }
            }
            true
        }
        navigation.selectedItemId = R.id.navigation_league
    }
}
