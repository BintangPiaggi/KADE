package com.bangkumist.bintang.footballapp.detailTeamFragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.model.detailTeam.DetailTeamItems
import com.bangkumist.bintang.footballapp.presenter.DetailTeamPresenter
import com.bangkumist.bintang.footballapp.view.DetailTeamView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team_detail.*


class TeamDetailFragment : Fragment(), DetailTeamView {
    private lateinit var prenseter: DetailTeamPresenter



    private lateinit var idLeague: String

    companion object {
        fun newInstance(leagueId: String): TeamDetailFragment {
            val fragment = TeamDetailFragment()
            fragment.idLeague = leagueId

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  view = inflater.inflate(R.layout.fragment_team_detail, container, false)
        val request = ApiRepository()
        val gson = Gson()
        prenseter = DetailTeamPresenter(this, request, gson)

        prenseter.getDetailTeam(idLeague)
        return view
    }

    override fun showDetailTeam(data: List<DetailTeamItems>) {
        tvInfoDetail.text = data[0].mDetailDescTeam
    }
}
