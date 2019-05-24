package com.bangkumist.bintang.footballapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.adapter.TeamAdapter
import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.model.detailLeague.TeamItems
import com.bangkumist.bintang.footballapp.presenter.SearchTeamPresenter
import com.bangkumist.bintang.footballapp.view.TeamView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_team.*
import org.jetbrains.anko.startActivity

class SearchTeamActivity : AppCompatActivity(), TeamView {
    private var event: MutableList<TeamItems> = mutableListOf()
    private lateinit var presenter: SearchTeamPresenter
    private lateinit var adapter: TeamAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)
        val rv = findViewById<RecyclerView>(R.id.rcSearchTeam)
        matchSearch(searchTeam)
        adapter = TeamAdapter(event) {
            onClickItem(it)
        }
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchTeamPresenter(this, request, gson)

    }


    override fun showMatch(event: List<TeamItems>) {
        this.event.clear()
        this.event.addAll(event)
        adapter.notifyDataSetChanged()
    }


    private fun matchSearch(searchView: android.support.v7.widget.SearchView) {
        searchView.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {

                presenter.getSearchTeam(text?: "")

                return true
            }
        })
    }
    private fun onClickItem(items: TeamItems) {
        startActivity<DetailTeamActivity>(
            "id" to items.mIdTeam,
            "name" to items.mNameTeam,
            "badge" to items.mTeamBadge
        )
    }
}
