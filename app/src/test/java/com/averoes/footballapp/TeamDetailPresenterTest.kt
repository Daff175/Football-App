package com.averoes.footballapp

import com.averoes.footballapp.mvp.presenter.TeamDetailPresenter
import com.averoes.footballapp.mvp.view.TeamDetailView
import com.averoes.footballapp.networking.InitRetrofit
import com.example.footballapp.mvp.model.Event.ResponseEvent
import com.example.footballapp.mvp.model.club.ResponseClub
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class TeamDetailPresenterTest {

    @Mock
    private lateinit var teamDetailPresenter: TeamDetailPresenter

    @Mock
    private lateinit var view: TeamDetailView

    @Mock
    private lateinit var service:InitRetrofit

    @Mock
    private lateinit var responseCLub:Response<ResponseClub>
    private lateinit var responseEvent:Response<ResponseEvent>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        teamDetailPresenter = TeamDetailPresenter(view)
    }

    @Test
    fun getTeamDetil(){

        GlobalScope.launch {
            Mockito.`when`(service.getInstance().getTeamDetail(ArgumentMatchers.anyString()))
                .thenReturn(responseCLub)

            Mockito.`when`(service.getInstance().getTeamDetail(""))
                .thenReturn(responseCLub)


            teamDetailPresenter.getTeamDetail("133604", true)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).hideLoading()
            Mockito.verify(view).showTeamDetail(responseCLub.body()?.teams, true)

        }
    }

    @Test
    fun getEventDetil(){
        GlobalScope.launch {
            Mockito.`when`(service.getInstance().getMatchDetail(ArgumentMatchers.anyString()))
                .thenReturn(responseEvent)

            Mockito.`when`(service.getInstance().getMatchDetail(""))
                .thenReturn(responseEvent)

            teamDetailPresenter.getEventDetail("441613")
            Mockito.verify(view).showLoading()
            Mockito.verify(view).hideLoading()
            Mockito.verify(view).showDetailEvent(responseEvent.body()?.events)
        }
    }
}