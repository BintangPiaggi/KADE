package com.bangkumist.bintang.footballapp.detailLeagueFragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.adapter.StandingsAdapter
import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.model.detailLeague.StandingsItems
import com.bangkumist.bintang.footballapp.presenter.StandingsPresenter
import com.bangkumist.bintang.footballapp.view.StandingsView
import com.google.gson.Gson


class StandingsFragment : Fragment(), StandingsView {

    private var league: MutableList<StandingsItems> = mutableListOf()
    private lateinit var presenter: StandingsPresenter
    private lateinit var adapter: StandingsAdapter
    private lateinit var id: String

    companion object {
        fun newInstance(leagueId: String): StandingsFragment {
            val fragment = StandingsFragment()
            fragment.id = leagueId

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_standings, container, false)
        val rv = view.findViewById<RecyclerView>(R.id.rcStandings)
        val request = ApiRepository()
        val gson = Gson()
        presenter = StandingsPresenter(this, request, gson)
        adapter = StandingsAdapter(league)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(context)
        setHasOptionsMenu(true)


        presenter.getStandingsList(id)
        return view
    }
    override fun showStandings(data: List<StandingsItems>) {
        league.clear()
        league.addAll(data)
        adapter.notifyDataSetChanged()
    }

}