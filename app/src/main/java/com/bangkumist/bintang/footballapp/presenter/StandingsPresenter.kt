package com.bangkumist.bintang.footballapp.presenter

import android.util.Log
import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.api.TheSportDBApi
import com.bangkumist.bintang.footballapp.model.detailLeague.StandingsResponse
import com.bangkumist.bintang.footballapp.test.CoroutineContextProvider
import com.bangkumist.bintang.footballapp.view.StandingsView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONException
import java.lang.Exception

class StandingsPresenter(
    private val view: StandingsView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {


    fun getStandingsList(id: String?) {


        GlobalScope.launch(context.main) {
            try {
                val data = gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getStandingsTeam(id)).await(),
                    StandingsResponse::class.java
                )
                data.table?.let { view.showStandings(it) }

            }catch (e: Exception ){
                Log.d("Error Klasemen", "Klasemen nya null")
            }
        }
    }
}