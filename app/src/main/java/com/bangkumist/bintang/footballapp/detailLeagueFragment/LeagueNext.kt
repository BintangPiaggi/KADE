package com.bangkumist.bintang.footballapp.detailLeagueFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ProgressBar
import com.bangkumist.bintang.footballapp.activity.DetailMatchActivity

import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.activity.SearchMatchActivity
import com.bangkumist.bintang.footballapp.adapter.MatchAdapter
import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.model.MatchItems
import com.bangkumist.bintang.footballapp.presenter.MatchPresenter
import com.bangkumist.bintang.footballapp.utils.invisible
import com.bangkumist.bintang.footballapp.utils.visible
import com.bangkumist.bintang.footballapp.view.MatchView
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.startActivity


class LeagueNext : Fragment(),MatchView {


    private var league: MutableList<MatchItems> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var id: String
    private lateinit var pb: ProgressBar

        companion object {
            fun newInstance(leagueId: String): LeagueNext {
                val fragment = LeagueNext()
                fragment.id = leagueId

                return fragment
            }
        }

            override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
                val view = inflater.inflate(R.layout.fragment_league_next, container, false)
                val rv = view.findViewById<RecyclerView>(R.id.rcLeagueNext)
                pb = view.findViewById(R.id.pbLeagueNext)
                val request = ApiRepository()
                val gson = Gson()
                presenter = MatchPresenter(this, request, gson)

                adapter = MatchAdapter(league){
                    onClickItem(it)
                }
                rv.adapter = adapter
                rv.layoutManager = LinearLayoutManager(context)
                setHasOptionsMenu(true)


                presenter.getNextMatch(id)
                return view
        }
    override fun showLoading() {
        pb.visible()
    }

    override fun hideLoading() {
        pb.invisible()
    }

    override fun showMatch(data: List<MatchItems>) {
        league.clear()
        league.addAll(data)
        adapter.notifyDataSetChanged()
    }
    private fun onClickItem(items: MatchItems) {
        startActivity<DetailMatchActivity>(
            "id" to items.mIdMatch,
            "home" to items.mHomeMatch,
            "away" to items.mAwayMatch,
            "homeScore" to items.mHomeScore,
            "awayScore" to  items.mAwayScore)
    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.searchId -> {
                startActivity<SearchMatchActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
