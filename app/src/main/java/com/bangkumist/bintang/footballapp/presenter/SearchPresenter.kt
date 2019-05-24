package com.bangkumist.bintang.footballapp.presenter

import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.api.TheSportDBApi
import com.bangkumist.bintang.footballapp.model.SearchResponse
import com.bangkumist.bintang.footballapp.test.CoroutineContextProvider
import com.bangkumist.bintang.footballapp.view.SearchMatchView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SearchPresenter(
    private val view: SearchMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getSearchMatch(league: String?) {

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getSearchMatch(league)).await(),
                SearchResponse::class.java)

            data.event?.let { it1 -> view.showMatch(it1) }
        }
    }
}