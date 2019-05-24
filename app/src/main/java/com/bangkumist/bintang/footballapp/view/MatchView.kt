package com.bangkumist.bintang.footballapp.view

import com.bangkumist.bintang.footballapp.model.MatchItems

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatch(data: List<MatchItems>)
}