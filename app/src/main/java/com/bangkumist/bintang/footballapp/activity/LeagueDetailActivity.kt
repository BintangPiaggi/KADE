package com.bangkumist.bintang.footballapp.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.adapter.ViewPagerAdapter
import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.detailLeagueFragment.LeagueNext
import com.bangkumist.bintang.footballapp.detailLeagueFragment.LeaguePrevious
import com.bangkumist.bintang.footballapp.detailLeagueFragment.StandingsFragment
import com.bangkumist.bintang.footballapp.detailLeagueFragment.TeamFragment
import com.bangkumist.bintang.footballapp.model.DetailLeagueItems
import com.bangkumist.bintang.footballapp.presenter.DetailLeaguePresenter
import com.bangkumist.bintang.footballapp.view.DetailLeagueView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_league_detail.*

class LeagueDetailActivity : AppCompatActivity(), DetailLeagueView {


    private lateinit var prenseter: DetailLeaguePresenter

    private lateinit var viewPagerr: ViewPager
    private lateinit var tabss: TabLayout

    private lateinit var idLeague: String
    private  var nameLeague: String? = null
    private  var photoLeague: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)

        val intent = intent
        idLeague = intent.getStringExtra("id")
        nameLeague = intent.getStringExtra("name")
        photoLeague = intent.getStringExtra("logo")

        supportActionBar?.title = "Detail League"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvNameLeagueDetail.text = nameLeague
        Picasso.get().load(photoLeague).into(imgLeagueDetail)
        supportActionBar
        val request = ApiRepository()
        val gson = Gson()
        prenseter = DetailLeaguePresenter(this, request, gson)

        prenseter.getDetailLeague(idLeague)


        viewPagerr = findViewById(R.id.viewpagerLeagueDetail)
        tabss = findViewById(R.id.tabLeagueDetail)
        setupViewPager(viewPagerr)

    }

    override fun showDetailLeague(data: List<DetailLeagueItems>) {
        tvFormedLeague.text = data[0].mFormedLeagueDetail
    }
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.populateFragment(TeamFragment.newInstance(idLeague), "Team")
        adapter.populateFragment(LeaguePrevious.newInstance(idLeague), "Previous Match")
        adapter.populateFragment(LeagueNext.newInstance(idLeague), "Next Match")
        adapter.populateFragment(StandingsFragment.newInstance(idLeague), "Standings")
        viewPager.adapter = adapter
        tabss.setupWithViewPager(viewPagerr)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
