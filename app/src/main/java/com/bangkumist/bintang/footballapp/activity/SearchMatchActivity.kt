package com.bangkumist.bintang.footballapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.adapter.MatchAdapter
import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.model.MatchItems
import com.bangkumist.bintang.footballapp.presenter.SearchPresenter
import com.bangkumist.bintang.footballapp.view.SearchMatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.startActivity

class SearchMatchActivity : AppCompatActivity(), SearchMatchView {
    private var event: MutableList<MatchItems> = mutableListOf()
    private lateinit var presenter: SearchPresenter
    private lateinit var adapter: MatchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)
        val rv = findViewById<RecyclerView>(R.id.rcSearch)
        matchSearch(searchMatch)
        adapter = MatchAdapter(event) {
            onClickItem(it)
        }
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchPresenter(this, request, gson)

    }


    override fun showMatch(event: List<MatchItems>) {
        this.event.clear()
        this.event.addAll(event)
        adapter.notifyDataSetChanged()
    }

    private fun onClickItem(items: MatchItems) {
        startActivity<DetailMatchActivity>(
            "id" to items.mIdMatch,
            "home" to items.mHomeMatch,
            "away" to items.mAwayMatch,
            "homeScore" to items.mHomeScore,
            "awayScore" to  items.mAwayScore
        )
    }

    private fun matchSearch(searchView: android.support.v7.widget.SearchView) {
        searchView.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {

                    presenter.getSearchMatch(text?: "")

                return true
            }
        })
    }
}