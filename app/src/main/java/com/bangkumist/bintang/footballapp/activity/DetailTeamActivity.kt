package com.bangkumist.bintang.footballapp.activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Menu
import android.view.MenuItem
import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.R.drawable.ic_add_to_favorites
import com.bangkumist.bintang.footballapp.R.drawable.ic_added_to_favorites
import com.bangkumist.bintang.footballapp.R.id.add_to_favorite
import com.bangkumist.bintang.footballapp.adapter.ViewPagerAdapter
import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.db.FavoriteTeam
import com.bangkumist.bintang.footballapp.db.database
import com.bangkumist.bintang.footballapp.detailTeamFragment.PlayerFragment
import com.bangkumist.bintang.footballapp.detailTeamFragment.TeamDetailFragment
import com.bangkumist.bintang.footballapp.model.detailLeague.TeamItems
import com.bangkumist.bintang.footballapp.model.detailTeam.DetailTeamItems
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select
import com.bangkumist.bintang.footballapp.presenter.DetailTeamPresenter
import com.bangkumist.bintang.footballapp.view.DetailTeamView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh

class DetailTeamActivity : AppCompatActivity(),DetailTeamView {


    private lateinit var prenseter: DetailTeamPresenter
    private var isFavorite: Boolean = false
    private lateinit var viewPagerr: ViewPager
    private lateinit var tabss: TabLayout
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var menuItem: Menu? = null
    private val table =FavoriteTeam

    private lateinit var idLeague: String
    private  var nameTeam: String? = null
    private  var photoTeam: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        swipeRefresh = findViewById(R.id.srDetailTeam)


        val intent = intent
        idLeague = intent.getStringExtra("id")
        nameTeam = intent.getStringExtra("name")
        photoTeam = intent.getStringExtra("badge")
        tvNameTeamDetail.text = nameTeam
        Picasso.get().load(photoTeam).into(imgTeamDetail)
        val request = ApiRepository()
        val gson = Gson()
        prenseter = DetailTeamPresenter(this, request, gson)

        prenseter.getDetailTeam(idLeague)

        favoriteState()
        setFavorite()
        viewPagerr = findViewById(R.id.vpTeamDetail)
        tabss = findViewById(R.id.tabTeamDetail)
        setupViewPager(viewPagerr)
        swipeRefresh.onRefresh {
            prenseter.getDetailTeam(idLeague)
        }
    }
    override fun showDetailTeam(data: List<DetailTeamItems>) {
        swipeRefresh.isRefreshing = false
        tvFormedTeam.text = data[0].mDetailFormedTeam
        tvAlternateTeam.text = data[0].mDetailAlternateTeam
    }
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.populateFragment(TeamDetailFragment.newInstance(idLeague), "Info")
        adapter.populateFragment(PlayerFragment.newInstance(idLeague), "Player")
        viewPager.adapter = adapter
        tabss.setupWithViewPager(viewPagerr)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to idLeague)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite() = try {
        database.use {
            insert(
                table.TABLE_FAVORITE_TEAM,
                table.TEAM_ID to idLeague,
                table.NAME_TEAM to nameTeam,
                table.PHOTO_TEAM to photoTeam)
        }
        swipeRefresh.snackbar("Added to favorite").show()
    } catch (e: SQLiteConstraintException){
        swipeRefresh.snackbar(e.localizedMessage).show()
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                    "id" to idLeague)
            }
            swipeRefresh.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }
    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
}
