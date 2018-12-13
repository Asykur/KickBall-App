package com.asykurkhamid.kickball.utils

import com.asykurkhamid.kickball.pojo.DataTeamPerItem

interface TeamsView{
    fun showLoading()
    fun hideLoading()
    fun showTeamList(dataTeams : List<DataTeamPerItem>?)
}