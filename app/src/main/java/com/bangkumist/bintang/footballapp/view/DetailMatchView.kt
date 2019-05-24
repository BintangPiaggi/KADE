package com.bangkumist.bintang.footballapp.view

import com.bangkumist.bintang.footballapp.model.MatchItems
import com.bangkumist.bintang.footballapp.model.Logo

interface DetailMatchView {
    fun showDetailMatch(data: List<MatchItems>)

    fun showHomeTeamLogo (data: List<Logo>)

    fun showAwayTeamLogo (data: List<Logo>)
}