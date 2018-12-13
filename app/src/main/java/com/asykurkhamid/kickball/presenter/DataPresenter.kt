package com.asykurkhamid.kickball.presenter

import com.asykurkhamid.kickball.pojo.*
import com.asykurkhamid.kickball.retrofit.ServiceFactory
import com.asykurkhamid.kickball.utils.LastMatchView
import com.asykurkhamid.kickball.utils.NextMatchView
import com.asykurkhamid.kickball.utils.TeamsView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class DataPresenter(private val viewLast: LastMatchView?,
                         private val viewNext: NextMatchView?,
                         private val viewTeams: TeamsView?) {

    //    =======================LAST MATCH========================
    fun getLastMatch(league_id: String, leagueName: String) {

        viewLast?.showLoading()
        val api = ServiceFactory().getInitInstance()
        val call = api.getLastData(league_id)

        call.enqueue(object : Callback<DataLastMatch> {
            override fun onFailure(call: Call<DataLastMatch>, t: Throwable) {
                viewLast?.hideLoading()
            }

            override fun onResponse(call: Call<DataLastMatch>, response: Response<DataLastMatch>) {
                if (response.isSuccessful) {
                    viewLast?.showLastMatchList(response.body()?.events, null)
                    getTeamLast(leagueName)
                }

            }
        })
    }

    fun getTeamLast(leagueName: String) {
        viewLast?.showLoading()
        val apiAllTeam = ServiceFactory().getInitInstance()
        val callAllTeam = apiAllTeam.getAllTeamData(leagueName)

        callAllTeam.enqueue(object : Callback<DataAllTeams> {
            override fun onFailure(call: Call<DataAllTeams>, t: Throwable) {
                viewLast?.hideLoading()
            }

            override fun onResponse(call: Call<DataAllTeams>, response: Response<DataAllTeams>) {
                if (response.isSuccessful) {
                    viewLast?.showLastMatchList(null, response.body()?.teams)
                    viewLast?.hideLoading()
                }
            }
        })
    }


    //    =======================NEXT MATCH========================
    fun getNextMatch(league_id: String, leagueName: String) {

        viewNext?.showLoading()
        val api = ServiceFactory().getInitInstance()
        val call = api.getNextData(league_id)

        call.enqueue(object : Callback<DataNextMatch> {
            override fun onFailure(call: Call<DataNextMatch>, t: Throwable) {
                viewNext?.hideLoading()
            }

            override fun onResponse(call: Call<DataNextMatch>, response: Response<DataNextMatch>) {
                viewNext?.showNextMatchList(response.body()?.events, null)
                getTeamNext(leagueName)
            }
        })
    }


    fun getTeamNext(leagueName: String) {
        viewNext?.showLoading()
        val apiAllTeam = ServiceFactory().getInitInstance()
        val callAllTeam = apiAllTeam.getAllTeamData(leagueName)

        callAllTeam.enqueue(object : Callback<DataAllTeams> {
            override fun onFailure(call: Call<DataAllTeams>, t: Throwable) {
                viewNext?.hideLoading()
            }

            override fun onResponse(call: Call<DataAllTeams>, response: Response<DataAllTeams>) {
                viewNext?.showNextMatchList(null, response.body()?.teams)
                viewNext?.hideLoading()
            }
        })
    }

    fun getTeams(leagueName: String) {
        viewTeams?.showLoading()
        val apiAllTeam = ServiceFactory().getInitInstance()
        val callAllTeam = apiAllTeam.getAllTeamData(leagueName)

        callAllTeam.enqueue(object : Callback<DataAllTeams> {
            override fun onFailure(call: Call<DataAllTeams>, t: Throwable) {
                viewTeams?.hideLoading()
            }

            override fun onResponse(call: Call<DataAllTeams>, response: Response<DataAllTeams>) {
                if (response.isSuccessful) {
                    viewTeams?.showTeamList(response.body()?.teams)
                    viewTeams?.hideLoading()
                }
            }
        })
    }


}