package com.bangkumist.bintang.footballapp.presenter

import com.bangkumist.bintang.footballapp.api.ApiRepository
import com.bangkumist.bintang.footballapp.model.MatchItems
import com.bangkumist.bintang.footballapp.model.SearchResponse
import com.bangkumist.bintang.footballapp.test.TestContextProvider
import com.bangkumist.bintang.footballapp.view.SearchMatchView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchPresenterTest {
    @Mock
    private lateinit var view: SearchMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: SearchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getSearchMatch() {
        val teams: MutableList<MatchItems> = mutableListOf()
        val response = SearchResponse(teams)
        val idMatch = "madrid"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    SearchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getSearchMatch(idMatch)

            Mockito.verify(view).showMatch(teams)
        }
    }
}