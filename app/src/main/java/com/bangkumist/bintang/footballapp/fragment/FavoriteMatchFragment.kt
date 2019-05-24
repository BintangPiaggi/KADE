package com.bangkumist.bintang.footballapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.activity.DetailMatchActivity
import com.bangkumist.bintang.footballapp.adapter.FavoriteAdapter
import com.bangkumist.bintang.footballapp.db.Favorite
import com.bangkumist.bintang.footballapp.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


class FavoriteMatchFragment : Fragment() {
    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteAdapter
    private lateinit var rv: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_favorite_match, container, false)
        rv = view.findViewById(R.id.rcFav)
        swipeRefresh = view.findViewById(R.id.sr_fav)
        rv.layoutManager = LinearLayoutManager(context)
        adapter = FavoriteAdapter(favorites){
            onClickItem(it)
        }
        showFavorite()
        rv.adapter = adapter
        swipeRefresh.onRefresh {
            showFavorite()
        }
        return view

    }


    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite(){
        swipeRefresh.isRefreshing = false
        favorites.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }
    private fun onClickItem(items: Favorite) {
        startActivity<DetailMatchActivity>(
            "id" to items.matchId,
            "home" to items.homeTeam,
            "away" to items.awayTeam,
            "homeScore" to items.homeTeamScore,
            "awayScore" to  items.awayTeamScore
        )
    }
}