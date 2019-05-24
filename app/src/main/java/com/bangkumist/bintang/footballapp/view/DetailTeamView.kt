package com.bangkumist.bintang.footballapp.view

import com.bangkumist.bintang.footballapp.model.detailTeam.DetailTeamItems
import com.bangkumist.bintang.footballapp.model.detailTeam.DetailTeamResponse

interface DetailTeamView {
    fun showDetailTeam(data: List<DetailTeamItems>)
}
