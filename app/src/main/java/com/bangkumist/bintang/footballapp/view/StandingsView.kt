package com.bangkumist.bintang.footballapp.view

import com.bangkumist.bintang.footballapp.model.DetailLeagueItems
import com.bangkumist.bintang.footballapp.model.detailLeague.StandingsItems

interface StandingsView {
    fun showStandings(data: List<StandingsItems>)
}