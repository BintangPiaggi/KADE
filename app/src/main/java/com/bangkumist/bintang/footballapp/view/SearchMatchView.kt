package com.bangkumist.bintang.footballapp.view

import com.bangkumist.bintang.footballapp.model.MatchItems

interface SearchMatchView {
    fun showMatch(event: List<MatchItems>)
}