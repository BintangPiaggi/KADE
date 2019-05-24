package com.bangkumist.bintang.footballapp.presenter

import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.model.MatchItems
import com.bangkumist.bintang.footballapp.model.MatchResponse
import com.bangkumist.bintang.footballapp.test.TestContextProvider
import com.bangkumist.bintang.footballapp.view.MatchView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchPresenterTest {
    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getPrevMatch() {
        val teams: MutableList<MatchItems> = mutableListOf()
        val response = MatchResponse(teams)
        val idLeague = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    MatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getPrevMatch(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatch(teams)
            Mockito.verify(view).hideLoading()
        }
    }


    @Test
    fun getNextMatch() {
        val teams: MutableList<MatchItems> = mutableListOf()
        val response = MatchResponse(teams)
        val idLeague = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    MatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getNextMatch(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatch(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}