package com.asykurkhamid.kickball.utils

import com.asykurkhamid.kickball.pojo.DataLastPerItem
import com.asykurkhamid.kickball.pojo.DataTeamPerItem

interface LastMatchView {
    fun showLoading()
    fun hideLoading()
    fun showLastMatchList(data : List<DataLastPerItem>?, datLastTeam : List<DataTeamPerItem>?)
}