package com.bangkumist.bintang.footballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.model.detailTeam.PlayerItems
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class PlayerAdapter(
    private val event: List<PlayerItems>,
    private val clickListener: (PlayerItems) -> Unit
) : RecyclerView.Adapter<PlayerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PlayerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_items, parent, false)
        return PlayerHolder(view)
    }

    override fun getItemCount(): Int = event.size

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.bindItem(event[position], clickListener)
    }
}

class PlayerHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvNamePlayer: TextView = view.find(R.id.tvNamePlayer)
    private val imgPlayer: ImageView = view.find(R.id.imgPlayer)
    private val  tvPositionPlayer : TextView = view.find(R.id.tvPositionPlayer)
    fun bindItem(item: PlayerItems,  clickListener: (PlayerItems) -> Unit) {
        tvNamePlayer.text = item.mPlayerName
        tvPositionPlayer.text = item.mPlayerPosition
        Picasso.get().load(item.mPlayerPhoto).into(imgPlayer)
        itemView.setOnClickListener {
            clickListener(item)
        }
    }
}