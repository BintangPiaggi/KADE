package com.bangkumist.bintang.footballapp.detailTeamFragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.activity.DetailPlayerActivity
import com.bangkumist.bintang.footballapp.activity.DetailTeamActivity
import com.bangkumist.bintang.footballapp.adapter.PlayerAdapter
import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.model.detailTeam.PlayerItems
import com.bangkumist.bintang.footballapp.presenter.PlayerPresenter
import com.bangkumist.bintang.footballapp.view.PlayerView
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.startActivity


class PlayerFragment : Fragment(), PlayerView {


    private var league: MutableList<PlayerItems> = mutableListOf()
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerAdapter
    private lateinit var idLeague: String

    companion object {
        fun newInstance(leagueId: String): PlayerFragment {
            val fragment = PlayerFragment()
            fragment.idLeague = leagueId

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_player, container, false)
        val rv = view.findViewById<RecyclerView>(R.id.rcPlayer)
        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)
        adapter = PlayerAdapter(league){
            onClickItem(it)
        }
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(context)
        setHasOptionsMenu(true)


        presenter.getPlayer(idLeague)
        return view
    }
    override fun showPlayer(data: List<PlayerItems>) {
        league.clear()
        league.addAll(data)
        adapter.notifyDataSetChanged()
    }
    private fun onClickItem(items: PlayerItems) {
        startActivity<DetailPlayerActivity>(
            "id" to items.mIdPlayer,
            "name" to items.mPlayerName,
            "photo" to items.mPlayerPhoto)
    }
}
