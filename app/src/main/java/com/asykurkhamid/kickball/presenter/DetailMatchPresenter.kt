package com.asykurkhamid.kickball.presenter

import com.asykurkhamid.kickball.pojo.DataTeams
import com.asykurkhamid.kickball.retrofit.ServiceFactory
import com.asykurkhamid.kickball.utils.DetailMatchView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMatchPresenter(private val detailLastView : DetailMatchView){


    fun getDetailHomeTeam(idTeam : String?){

        detailLastView.showLoading()
        val api = ServiceFactory().getInitInstance()
        val call = api.getTeamData(idTeam)

        call.enqueue(object : Callback<DataTeams>{
            override fun onFailure(call: Call<DataTeams>, t: Throwable) {
                detailLastView.hideLoading()
            }

            override fun onResponse(call: Call<DataTeams>, response: Response<DataTeams>) {
                detailLastView.showHomeBadge(response.body()?.teams)
                detailLastView.hideLoading()
            }

        })
    }
    fun getDetailAwayTeam(idTeam : String?){

        detailLastView.showLoading()
        val api = ServiceFactory().getInitInstance()
        val call = api.getTeamData(idTeam)

        call.enqueue(object : Callback<DataTeams>{
            override fun onFailure(call: Call<DataTeams>, t: Throwable) {
                detailLastView.hideLoading()
            }

            override fun onResponse(call: Call<DataTeams>, response: Response<DataTeams>) {
                detailLastView.showAwayBadge(response.body()?.teams)
                detailLastView.hideLoading()
            }

        })
    }
}