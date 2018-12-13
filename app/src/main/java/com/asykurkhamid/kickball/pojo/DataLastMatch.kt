package com.asykurkhamid.kickball.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DataLastMatch(val events : List<DataLastPerItem> ) : Parcelable

@Parcelize
data class DataLastPerItem(
        val idEvent                   : String? = null,
        val strEvent                  : String? = null,
        val idLeague                  : String? = null,
        val strHomeTeam               : String? = null,
        val strAwayTeam               : String? = null,
        val intHomeScore              : String? = null,
        val intAwayScore              : String? = null,
        val strHomeGoalDetails        : String? = null,
        val strHomeRedCards           : String? = null,
        val strHomeYellowCards        : String? = null,
        val strHomeLineupGoalkeeper   : String? = null,
        val strHomeLineupDefense      : String? = null,
        val strHomeLineupMidfield     : String? = null,
        val strHomeLineupForward      : String? = null,
        val strAwayRedCards           : String? = null,
        val strAwayYellowCards        : String? = null,
        val strAwayGoalDetails        : String? = null,
        val strAwayLineupGoalkeeper   : String? = null,
        val strAwayLineupDefense      : String? = null,
        val strAwayLineupMidfield     : String? = null,
        val strAwayLineupForward      : String? = null,
        val dateEvent                 : String? = null,
        val strDate                   : String? = null,
        val strTime                   : String? = null,
        val idHomeTeam                : String? = null,
        val idAwayTeam                : String? = null
) : Parcelable