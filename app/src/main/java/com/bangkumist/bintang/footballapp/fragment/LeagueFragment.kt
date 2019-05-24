package com.bangkumist.bintang.footballapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.bangkumist.bintang.footballapp.activity.LeagueDetailActivity
import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.adapter.LeagueAdapter
import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.model.LeagueItems
import com.bangkumist.bintang.footballapp.presenter.LeaguePresenter
import com.bangkumist.bintang.footballapp.utils.invisible
import com.bangkumist.bintang.footballapp.utils.visible
import com.bangkumist.bintang.footballapp.view.LeagueView
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class LeagueFragment : Fragment(), LeagueView {


    private var league: MutableList<LeagueItems> = mutableListOf()
    private lateinit var presenter: LeaguePresenter
    private lateinit var adapter: LeagueAdapter
    private lateinit var pb: ProgressBar
    private lateinit var sr: SwipeRefreshLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_league, container, false)
        val rv = view.findViewById<RecyclerView>(R.id.rcLeague)
        pb = view.findViewById(R.id.pb_league)
        sr = view.findViewById(R.id.sr_league)

        adapter = LeagueAdapter(league) {
            onClickItem(it)
        }
        rv.adapter = adapter
        rv.layoutManager = GridLayoutManager(context, 2)

        val request = ApiRepository()
        val gson = Gson()
        presenter = LeaguePresenter(this, request, gson)

        presenter.getLeagueList("Soccer")
        sr.onRefresh {
            presenter.getLeagueList("Soccer")
        }
        return view
    }

    override fun showLoading() {
        pb.visible()
    }

    override fun hideLoading() {
        pb.invisible()
    }

    override fun showLeague(data: List<LeagueItems>) {
        sr.isRefreshing = false
        league.clear()
        league.addAll(data)
        adapter.notifyDataSetChanged()
    }
    private fun onClickItem(items: LeagueItems) {
        startActivity<LeagueDetailActivity>(
            "id" to items.mIdLeague,
            "name" to items.mNameLeague,
            "logo" to items.mLogoLeague
        )
    }

}
