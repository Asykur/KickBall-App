package com.asykurkhamid.kickball.pojo

import com.google.gson.annotations.SerializedName

class DataTeams {

    @SerializedName("teams")
    var teams: List<ItemTeams>? = null

    inner class ItemTeams {
        @SerializedName("idTeam")
        var idTeam: String? = null

        @SerializedName("idLeague")
        var idLeague: String? = null

        @SerializedName("strTeamBadge")
        var strTeamBadge: String? = null

        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String? = null

    }
}