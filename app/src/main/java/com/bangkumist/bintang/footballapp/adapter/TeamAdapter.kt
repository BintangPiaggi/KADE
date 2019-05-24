package com.bangkumist.bintang.footballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.model.detailLeague.TeamItems
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class TeamAdapter(
    private val event: List<TeamItems>,
    private val clickListener: (TeamItems) -> Unit
) : RecyclerView.Adapter<TeamHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TeamHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.team_items, parent, false)
        return TeamHolder(view)
    }

    override fun getItemCount(): Int = event.size

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        holder.bindItem(event[position], clickListener)
    }
}

class TeamHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvName: TextView = view.find(R.id.tvNameTeam)
    private val imgTeam: ImageView = view.find(R.id.imgTeam)
    fun bindItem(item: TeamItems,  clickListener: (TeamItems) -> Unit) {
        tvName.text = item.mNameTeam
        Picasso.get().load(item.mTeamBadge).into(imgTeam)
        itemView.setOnClickListener {
            clickListener(item)
        }
    }
}
