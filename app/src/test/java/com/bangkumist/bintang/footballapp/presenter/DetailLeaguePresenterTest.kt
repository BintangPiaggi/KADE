package com.bangkumist.bintang.footballapp.presenter

import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.model.DetailLeagueItems
import com.bangkumist.bintang.footballapp.model.DetailLeagueResponse
import com.bangkumist.bintang.footballapp.test.TestContextProvider
import com.bangkumist.bintang.footballapp.view.DetailLeagueView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailLeaguePresenterTest {
    @Mock
    private lateinit var view: DetailLeagueView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: DetailLeaguePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailLeaguePresenter(view, apiRepository, gson, TestContextProvider())

    }

    @Test
    fun getDetailLeague() {
        val teams: MutableList<DetailLeagueItems> = mutableListOf()
        val response = DetailLeagueResponse(teams)
        val idLeague = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    DetailLeagueResponse::class.java
                )
            ).thenReturn(response)

            presenter.getDetailLeague(idLeague)

            Mockito.verify(view).showDetailLeague(teams)

        }
    }
}