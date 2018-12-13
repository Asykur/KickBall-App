package com.asykurkhamid.kickball.utils

import com.asykurkhamid.kickball.pojo.DataTeams

interface DetailMatchView {

    fun showLoading()
    fun hideLoading()
    fun showHomeBadge(data : List<DataTeams.ItemTeams>?)
    fun showAwayBadge(data : List<DataTeams.ItemTeams>?)
}