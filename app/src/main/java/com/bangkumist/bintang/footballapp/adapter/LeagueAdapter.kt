package com.bangkumist.bintang.footballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.model.LeagueItems
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class LeagueAdapter(
    private val leagues: List<LeagueItems>,
    private val clickListener: (LeagueItems) -> Unit
) : RecyclerView.Adapter<LeagueHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): LeagueHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.league_items, parent, false)
        return LeagueHolder(view)
    }

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(holder: LeagueHolder, position: Int) {
        holder.bindItem(leagues[position], clickListener)
    }

}

class LeagueHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val leagueLogo: ImageView = view.find(R.id.imgLogoleague)
    private val leagueName: TextView = view.find(R.id.tvNameLeague)

    fun bindItem(leagues: LeagueItems, clickListener: (LeagueItems) -> Unit) {
        Picasso.get().load(leagues.mLogoLeague).into(leagueLogo)
        leagueName.text = leagues.mNameLeague
        itemView.setOnClickListener {
            clickListener(leagues)
        }
    }

}
