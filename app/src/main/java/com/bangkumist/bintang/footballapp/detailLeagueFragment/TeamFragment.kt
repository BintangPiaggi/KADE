package com.bangkumist.bintang.footballapp.detailLeagueFragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*

import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.activity.DetailTeamActivity
import com.bangkumist.bintang.footballapp.activity.SearchTeamActivity
import com.bangkumist.bintang.footballapp.adapter.TeamAdapter
import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.model.detailLeague.TeamItems
import com.bangkumist.bintang.footballapp.presenter.TeamPresenter
import com.bangkumist.bintang.footballapp.view.TeamView
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.startActivity


class TeamFragment : Fragment(), TeamView {

    private var league: MutableList<TeamItems> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamAdapter
    private lateinit var idLeague: String

    companion object {
        fun newInstance(leagueId: String): TeamFragment {
            val fragment = TeamFragment()
            fragment.idLeague = leagueId

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_team, container, false)
        val rv = view.findViewById<RecyclerView>(R.id.rcTeam)
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)
        adapter = TeamAdapter(league){

                onClickItem(it)

        }
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(context)
        setHasOptionsMenu(true)


        presenter.getTeamList(idLeague)
        return view
    }
    override fun showMatch(data: List<TeamItems>) {
        league.clear()
        league.addAll(data)
        adapter.notifyDataSetChanged()
    }
    private fun onClickItem(items: TeamItems) {
        startActivity<DetailTeamActivity>(
            "id" to items.mIdTeam,
            "name" to items.mNameTeam,
            "badge" to items.mTeamBadge
        )
    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.searchId -> {
                startActivity<SearchTeamActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
