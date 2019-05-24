package com.bangkumist.bintang.footballapp.presenter

import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.api.TheSportDBApi
import com.bangkumist.bintang.footballapp.model.LeagueResponse
import com.bangkumist.bintang.footballapp.test.CoroutineContextProvider
import com.bangkumist.bintang.footballapp.view.LeagueView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LeaguePresenter(
    private val view: LeagueView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {


    fun getLeagueList(league: String?) {
        view.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLeague(league)).await(),
                LeagueResponse::class.java)

            view.showLeague(data.countrys)
            view.hideLoading()
        }
    }
}