package com.bangkumist.bintang.footballapp.view

import com.bangkumist.bintang.footballapp.model.detailLeague.TeamItems

interface TeamView {
    fun showMatch(data: List<TeamItems>)
}