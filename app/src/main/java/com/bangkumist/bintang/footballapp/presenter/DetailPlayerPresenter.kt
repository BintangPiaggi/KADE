package com.bangkumist.bintang.footballapp.presenter

import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.api.TheSportDBApi
import com.bangkumist.bintang.footballapp.model.detailTeam.DetailPlayerResponse
import com.bangkumist.bintang.footballapp.test.CoroutineContextProvider
import com.bangkumist.bintang.footballapp.view.DetailPlayerView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailPlayerPresenter(
    private val view: DetailPlayerView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getDetailPlayer(league: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getDetailPlayer(league)).await(),
                DetailPlayerResponse::class.java
            )
            data.players?.let { view.showDetailPlayer(it) }
        }
    }
}