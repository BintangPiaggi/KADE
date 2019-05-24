package com.bangkumist.bintang.footballapp.view

import com.bangkumist.bintang.footballapp.model.detailTeam.DetailPlayerItems

interface DetailPlayerView {
    fun showDetailPlayer(data: List<DetailPlayerItems>)
}