package com.bangkumist.bintang.footballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.db.FavoriteTeam
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class FavoriteTeamAdapter(
    private val event: List<FavoriteTeam>,
    private val clickListener: (FavoriteTeam) -> Unit
) : RecyclerView.Adapter<FavoriteTeamHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FavoriteTeamHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.team_items, parent, false)
        return FavoriteTeamHolder(view)
    }

    override fun getItemCount(): Int = event.size

    override fun onBindViewHolder(holder: FavoriteTeamHolder, position: Int) {
        holder.bindItem(event[position], clickListener)
    }
}

class FavoriteTeamHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvName: TextView = view.find(R.id.tvNameTeam)
    private val imgTeam: ImageView = view.find(R.id.imgTeam)
    fun bindItem(item: FavoriteTeam,  clickListener: (FavoriteTeam) -> Unit) {
        tvName.text = item.nameTeam
        Picasso.get().load(item.photoTeam).into(imgTeam)
        itemView.setOnClickListener {
            clickListener(item)
        }
    }
}