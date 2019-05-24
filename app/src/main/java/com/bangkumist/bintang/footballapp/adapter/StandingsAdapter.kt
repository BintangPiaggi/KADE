package com.bangkumist.bintang.footballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.model.detailLeague.StandingsItems
import org.jetbrains.anko.find

class StandingsAdapter(
    private val event: List<StandingsItems>
) : RecyclerView.Adapter<StandingsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): StandingsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.standings_items, parent, false)
        return StandingsHolder(view)
    }

    override fun getItemCount(): Int = event.size

    override fun onBindViewHolder(holder: StandingsHolder, position: Int) {
        holder.bindItem(event[position])

    }
}

class StandingsHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvName  : TextView = view.find(R.id.tvStandingsClub)
    private val tvPlay  : TextView = view.find(R.id.tvStandingsPlayed)
    private val tvGoals : TextView = view.find(R.id.tvStandingsGoal)
    private val tvWin   : TextView = view.find(R.id.tvStandingsWin)
    private val tvDraw  : TextView = view.find(R.id.tvStandingsDraw)
    private val tvLose  : TextView = view.find(R.id.tvStandingsLose)
    private val tvPoint : TextView = view.find(R.id.tvStandingsPoint)
    fun bindItem(item: StandingsItems) {
        tvName.text     = item.mStandingsName
        tvPlay.text     = item.mStandingsPlayed
        tvGoals.text    = item.mStandingsGoals
        tvWin.text      = item.mStandingsWin
        tvDraw.text     = item.mStandingsDraw
        tvLose.text     = item.mStandingsLose
        tvPoint.text    = item.mStandingsPoint

    }
}
