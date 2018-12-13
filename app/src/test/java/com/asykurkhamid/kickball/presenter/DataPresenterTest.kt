package com.asykurkhamid.kickball.presenter

import com.asykurkhamid.kickball.pojo.DataAllTeams
import com.asykurkhamid.kickball.pojo.DataLastMatch
import com.asykurkhamid.kickball.pojo.DataNextMatch
import com.asykurkhamid.kickball.retrofit.ServiceFactory
import com.asykurkhamid.kickball.utils.LastMatchView
import com.asykurkhamid.kickball.utils.NextMatchView
import com.asykurkhamid.kickball.utils.Variables
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataPresenterTest {

    @Mock
    private lateinit var viewLast: LastMatchView
    @Mock
    private lateinit var viewNext : NextMatchView
    @Mock
    private lateinit var presenter: DataPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenter = DataPresenter(viewLast,viewNext)
    }

    @Test
    fun getLastMatch() {
        val api = ServiceFactory().getInitInstance()
        val call = api.getLastData(Variables.LEAGUE_ID)


        call.enqueue(object : Callback<DataLastMatch> {
            override fun onFailure(call: Call<DataLastMatch>, t: Throwable) {

            }

            override fun onResponse(call: Call<DataLastMatch>, response: Response<DataLastMatch>) {
                if (response.isSuccessful){
                    presenter.getLastMatch(Variables.LEAGUE_ID, Variables.LEAGUE_NAME)
                    Mockito.verify(viewLast.showLoading())
                    Mockito.verify(viewLast.hideLoading())
                    Mockito.verify(viewLast.showLastMatchList(response.body()?.events,null))
                }

            }
        })
    }

    @Test
    fun getTeamLast() {
        val apiAllTeam = ServiceFactory().getInitInstance()
        val callAllTeam = apiAllTeam.getAllTeamData(Variables.LEAGUE_NAME)

        callAllTeam.enqueue(object : Callback<DataAllTeams> {
            override fun onFailure(call: Call<DataAllTeams>, t: Throwable) {

            }

            override fun onResponse(call: Call<DataAllTeams>, response: Response<DataAllTeams>) {
                if (response.isSuccessful){
                    presenter.getTeamLast(Variables.LEAGUE_NAME)
                    Mockito.verify(viewLast.showLoading())
                    Mockito.verify(viewLast.hideLoading())
                    Mockito.verify(viewLast.showLastMatchList(null,response.body()?.teams))
                }
            }
        })
    }

    @Test
    fun getNextMatch() {
        val api = ServiceFactory().getInitInstance()
        val call = api.getNextData(Variables.LEAGUE_ID)

        call.enqueue(object : Callback<DataNextMatch> {
            override fun onFailure(call: Call<DataNextMatch>, t: Throwable) {

            }

            override fun onResponse(call: Call<DataNextMatch>, response: Response<DataNextMatch>) {
                presenter.getNextMatch(Variables.LEAGUE_ID,Variables.LEAGUE_NAME)

                Mockito.verify(viewNext.showLoading())
                Mockito.verify(viewNext.hideLoading())
                Mockito.verify(viewNext.showNextMatchList(response.body()?.events,null))
            }
        })
    }

    @Test
    fun getTeamNext() {
        val apiAllTeam = ServiceFactory().getInitInstance()
        val callAllTeam = apiAllTeam.getAllTeamData(Variables.LEAGUE_NAME)

        callAllTeam.enqueue(object : Callback<DataAllTeams> {
            override fun onFailure(call: Call<DataAllTeams>, t: Throwable) {

            }

            override fun onResponse(call: Call<DataAllTeams>, response: Response<DataAllTeams>) {
                presenter.getTeamNext(Variables.LEAGUE_NAME)

                Mockito.verify(viewNext.showLoading())
                Mockito.verify(viewNext.hideLoading())
                Mockito.verify(viewNext.showNextMatchList(null,response.body()?.teams))
            }
        })
    }
}