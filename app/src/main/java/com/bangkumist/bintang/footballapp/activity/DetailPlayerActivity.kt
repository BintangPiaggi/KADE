package com.bangkumist.bintang.footballapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.model.detailTeam.DetailPlayerItems
import com.bangkumist.bintang.footballapp.presenter.DetailPlayerPresenter
import com.bangkumist.bintang.footballapp.view.DetailPlayerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity(), DetailPlayerView {



    private lateinit var prenseter: DetailPlayerPresenter

    private lateinit var idPlayer: String
    private  var name: String? = null
    private  var photo: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        idPlayer = intent.getStringExtra("id")
        name = intent.getStringExtra("name")
        photo = intent.getStringExtra("photo")

        tvDetailNamePlayer.text = name
        Picasso.get().load(photo).into(imgDetailPlayer)

        val request = ApiRepository()
        val gson = Gson()
        prenseter = DetailPlayerPresenter(this, request, gson)
        prenseter.getDetailPlayer(idPlayer)

    }
    override fun showDetailPlayer(data: List<DetailPlayerItems>) {
        tvDetailNationPlayer.text = data[0].mNationPlayerDetail
        tvDetailDescPlayer.text = data[0].mDescPlayerDetail
        Picasso.get().load(data[0].mFanArt).into(imgFanArtPlayer)
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
