package com.bangkumist.bintang.footballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.model.MatchItems
import org.jetbrains.anko.find

class MatchAdapter(
    private val event: List<MatchItems>,
    private val clickListener: (MatchItems) -> Unit
) : RecyclerView.Adapter<MatchHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MatchHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.match_items, parent, false)
        return MatchHolder(view)
    }

    override fun getItemCount(): Int = event.size

    override fun onBindViewHolder(holder: MatchHolder, position: Int) {
        holder.bindItem(event[position], clickListener)

    }
}

class MatchHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvHome: TextView = view.find(R.id.tvRcHome)
    private val tvAway: TextView = view.find(R.id.tvRcAway)
    private val tvHomeScore: TextView = view.find(R.id.tvRcHomeScore)
    private val tvAwayScore: TextView = view.find(R.id.tvRcAwayScore)
    private val tvDate: TextView = view.find(R.id.tvDateMatch)
    private val tvTime: TextView = view.find(R.id.tvTimeMatch)
    fun bindItem(item: MatchItems, clickListener: (MatchItems) -> Unit) {
        tvHome.text = item.mHomeMatch
        tvAway.text = item.mAwayMatch
        tvHomeScore.text = item.mHomeScore
        tvAwayScore.text = item.mAwayScore
        tvDate.text = item.mDateMatch
        tvTime.text = item.mTimeMatch
        itemView.setOnClickListener {
            clickListener(item)
        }
    }
}
