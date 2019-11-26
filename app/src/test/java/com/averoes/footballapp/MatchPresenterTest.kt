package com.averoes.footballapp

import com.averoes.footballapp.mvp.model.event.EventsItem
import com.averoes.footballapp.mvp.model.event.ResponseSearch
import com.averoes.footballapp.mvp.presenter.MatchPresenter
import com.averoes.footballapp.mvp.view.MatchView
import com.averoes.footballapp.networking.InitRetrofit
import com.example.footballapp.mvp.model.Event.ResponseEvent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class MatchPresenterTest {

    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var presenter: MatchPresenter

    @Mock
    private lateinit var service:InitRetrofit

    @Mock
    private lateinit var response: Response<ResponseEvent>

    @Mock lateinit var searchResponse: Response<ResponseSearch>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view)
    }

    @Test
    fun getLastMatch(){

        val match : MutableList<EventsItem> = mutableListOf()

       GlobalScope.launch {

           Mockito.`when`(service.getInstance().getLastMatch(ArgumentMatchers.anyString()))
               .thenReturn(response)

           Mockito.`when`(service.getInstance().getLastMatch(""))

           presenter.getLastMatch(4328)
           Mockito.verify(view).hideLoading()
           Mockito.verify(view).showMatchList(match)
           Mockito.verify(view).showMessage("succes")
           Mockito.verify(view).showLoading()
       }

    }

    @Test
    fun getNextMatch(){

        val match : MutableList<EventsItem> = mutableListOf()

        GlobalScope.launch {

            Mockito.`when`(service.getInstance().getNextMatch(ArgumentMatchers.anyString()))
                .thenReturn(response)

            Mockito.`when`(service.getInstance().getNextMatch(""))

            presenter.getNextMatch(4328)
            Mockito.verify(view).hideLoading()
            Mockito.verify(view).showMatchList(match)
            Mockito.verify(view).showMessage("succes")
            Mockito.verify(view).showLoading()
        }

    }

    @Test
    fun searchEvent(){

        val match : MutableList<EventsItem> = mutableListOf()

        GlobalScope.launch {

            Mockito.`when`(service.getInstance().searchEvent(ArgumentMatchers.anyString()))
                .thenReturn(searchResponse)

            Mockito.`when`(service.getInstance().searchEvent(""))

            presenter.searchMatch("everton")
            Mockito.verify(view).hideLoading()
            Mockito.verify(view).showMatchList(match)
            Mockito.verify(view).showMessage("succes")
            Mockito.verify(view).showLoading()
        }

    }

}
