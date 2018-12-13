package com.asykurkhamid.kickball.utils

import com.asykurkhamid.kickball.pojo.DataLastPerItem
import com.asykurkhamid.kickball.pojo.DataNextPerItem
import com.asykurkhamid.kickball.pojo.DataTeamPerItem

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showNextMatchList(dataNext : List<DataNextPerItem>?, dataTeam : List<DataTeamPerItem>?)
}