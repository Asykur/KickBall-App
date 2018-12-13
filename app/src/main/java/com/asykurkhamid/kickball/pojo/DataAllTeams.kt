package com.asykurkhamid.kickball.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DataAllTeams(var teams: List<DataTeamPerItem>):Parcelable

@Parcelize
data class DataTeamPerItem (
        val idTeam                   : String? = null,
        val strTeamBadge             : String? = null,
        val strTeam                  : String? = null,
        val strDescriptionEN         : String? = null
) : Parcelable
