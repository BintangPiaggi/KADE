package com.bangkumist.bintang.footballapp.presenter

import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.api.TheSportDBApi
import com.bangkumist.bintang.footballapp.model.DetailMatchResponse
import com.bangkumist.bintang.footballapp.model.LogoResponse
import com.bangkumist.bintang.footballapp.test.CoroutineContextProvider
import com.bangkumist.bintang.footballapp.view.DetailMatchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailMatchPresenter(
    private val view: DetailMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getDetailMatch(league: String?) {

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getDetailMatch(league)).await(),
                DetailMatchResponse::class.java)

            view.showDetailMatch(data.events)
        }
    }

    fun getTeamLogo(league: String?, teamType: String?) {

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamLogo(league)).await(),
                LogoResponse::class.java)

            if(teamType == "Home")
                view.showHomeTeamLogo(data.teams)
            else
                view.showAwayTeamLogo(data.teams)
        }
    }
}