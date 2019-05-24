package com.bangkumist.bintang.footballapp.view

import com.bangkumist.bintang.footballapp.model.LeagueItems

interface LeagueView {
    fun showLoading()
    fun hideLoading()
    fun showLeague(data: List<LeagueItems>)
}