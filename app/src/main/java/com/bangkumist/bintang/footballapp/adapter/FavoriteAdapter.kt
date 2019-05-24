package com.bangkumist.bintang.footballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.db.Favorite
import org.jetbrains.anko.find

class FavoriteAdapter( private val favorite: List<Favorite>,
private val clickListener: (Favorite) -> Unit
) : RecyclerView.Adapter<FavoriteHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FavoriteHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.match_items, parent, false)
        return FavoriteHolder(view)
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bindItem(favorite[position], clickListener)
    }
}

class FavoriteHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvHome: TextView = view.find(R.id.tvRcHome)
    private val tvAway: TextView = view.find(R.id.tvRcAway)
    private val tvHomeScore: TextView = view.find(R.id.tvRcHomeScore)
    private val tvAwayScore: TextView = view.find(R.id.tvRcAwayScore)
    fun bindItem(favoritess: Favorite, clickListener: (Favorite) -> Unit) {
        tvHome.text = favoritess.homeTeam
        tvAway.text = favoritess.awayTeam
        tvHomeScore.text = favoritess.homeTeamScore
        tvAwayScore.text = favoritess.awayTeamScore
        itemView.setOnClickListener {
            clickListener(favoritess)
        }
    }
}
