package com.bangkumist.bintang.footballapp.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.R.drawable.ic_add_to_favorites
import com.bangkumist.bintang.footballapp.R.drawable.ic_added_to_favorites
import com.bangkumist.bintang.footballapp.R.id.add_to_favorite
import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.db.Favorite
import com.bangkumist.bintang.footballapp.db.database
import com.bangkumist.bintang.footballapp.model.MatchItems
import com.bangkumist.bintang.footballapp.model.Logo
import com.bangkumist.bintang.footballapp.presenter.DetailMatchPresenter
import com.bangkumist.bintang.footballapp.view.DetailMatchView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {



    private lateinit var prenseter: DetailMatchPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var idLeague: String
    private  var homeTeam: String? = null
    private  var homeScore: String? = null
    private  var awayTeam: String? = null
    private  var awayScore: String? = null

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(MATCH_ID = {id})",
                    "id" to idLeague)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        swipeRefresh = findViewById(R.id.swipeDetail)

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        idLeague = intent.getStringExtra("id")
        homeTeam = intent.getStringExtra("home")
        homeScore = intent.getStringExtra("homeScore")
        awayTeam = intent.getStringExtra("away")
        awayScore = intent.getStringExtra("awayScore")

        if(homeScore.equals(null) || awayScore.equals(null)) {
            homeScore = "-"
            awayScore = "-"}

        tvHomeDetail.text       = homeTeam
        tvAwayDetail.text       = awayTeam
        tvHomeDetailScore.text  = homeScore
        tvAwayDetailScore.text  = awayScore



        val request = ApiRepository()
        val gson = Gson()
        prenseter = DetailMatchPresenter(this, request, gson)
        prenseter.getDetailMatch(idLeague)

        prenseter.getTeamLogo(homeTeam, "Home")
        prenseter.getTeamLogo(awayTeam, "Away")


        favoriteState()
        swipeRefresh.onRefresh {
            prenseter.getDetailMatch(idLeague)
        }
    }

    override fun showDetailMatch(data: List<MatchItems>) {
        swipeRefresh.isRefreshing = false
        tvHomeGoals.text        = data[0].mHomeGoal
        tvAwayGoals.text        = data[0].mAwayGoal
        tvHomeDef.text          = data[0].mHomeDef
        tvAwayDef.text          = data[0].mAwayDef
        tvHomeFw.text           = data[0].mHomeFw
        tvAwayFw.text           = data[0].mAwayFw
        tvHomeGK.text           = data[0].mHomeGK
        tvAwayGK.text           = data[0].mAwayGk
        tvHomeMid.text          = data[0].mHomeMid
        tvAwayMid.text          = data[0].mAwayMid
        tvHomeSubs.text         = data[0].mHomeSub
        tvAwaySubs.text         = data[0].mAwaySub
        tvHomeYellow.text       = data[0].mHomeYellow
        tvAwayYellow.text       = data[0].mAwayYellow
    }

    override fun showHomeTeamLogo(data: List<Logo>) {
        Picasso.get().load(data[0].mBadge).into(imgTeamLogoHome)
    }

    override fun showAwayTeamLogo(data: List<Logo>) {
        Picasso.get().load(data[0].mBadge).into(imgTeamLogoAway)
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

    private fun addToFavorite() = try {
        database.use {
            insert(
                Favorite.TABLE_FAVORITE,
                Favorite.MATCH_ID to idLeague,
                Favorite.HOME_TEAM to homeTeam,
                Favorite.AWAY_TEAM to awayTeam,
                Favorite.HOME_TEAM_SCORE to  homeScore,
                Favorite.AWAY_TEAM_SCORE to awayScore)
        }
        swipeRefresh.snackbar("Added to favorite").show()
    } catch (e: SQLiteConstraintException){
        swipeRefresh.snackbar(e.localizedMessage).show()
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(MATCH_ID = {id})",
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
