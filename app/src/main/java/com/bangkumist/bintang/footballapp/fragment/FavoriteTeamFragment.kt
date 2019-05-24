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
import com.bangkumist.bintang.footballapp.activity.DetailTeamActivity
import com.bangkumist.bintang.footballapp.adapter.FavoriteTeamAdapter
import com.bangkumist.bintang.footballapp.db.FavoriteTeam
import com.bangkumist.bintang.footballapp.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


class FavoriteTeamFragment : Fragment() {

    private var favorites: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: FavoriteTeamAdapter
    private lateinit var rv: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_team, container, false)
        rv = view.findViewById(R.id.rcFavTeam)
        swipeRefresh = view.findViewById(R.id.sr_favo)
        rv.layoutManager = LinearLayoutManager(context)
        adapter = FavoriteTeamAdapter(favorites) {
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

    private fun showFavorite() {
        swipeRefresh.isRefreshing = false
        favorites.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    private fun onClickItem(items: FavoriteTeam) {
        startActivity<DetailTeamActivity>(
            "id" to items.teamId,
            "name" to items.nameTeam,
            "badge" to items.photoTeam
        )
    }
}