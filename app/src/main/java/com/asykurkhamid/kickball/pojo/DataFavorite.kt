package com.asykurkhamid.kickball.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataFavorite(val id: Int?,
                        val eventID                   : String?,
                        val homeID                    : String?,
                        val awayID                    : String?,
                        val homeTeamName              : String?,
                        val awayTeamName              : String?,
                        val homeScore                 : String?,
                        val awayScore                 : String?,
                        val dateEvent                 : String?,
                        val timeEvent                 : String?,
                        val strHomeGoalDetails        : String?,
                        val strAwayGoalDetails        : String?,
                        val strHomeLineupGoalkeeper   : String?,
                        val strAwayLineupGoalkeeper   : String?,
                        val strHomeLineupDefense      : String?,
                        val strAwayLineupDefense      : String?,
                        val strHomeLineupMidfield     : String?,
                        val strAwayLineupMidfield     : String?,
                        val strHomeLineupForward      : String?,
                        val strAwayLineupForward      : String?,
                        val strHomeYellowCards        : String?,
                        val strAwayYellowCards        : String?,
                        val strHomeRedCards           : String?,
                        val strAwayRedCards           : String?,
                        val strLeague                 : String?,
                        val category                  : String?
) : Parcelable {

        companion object {
        //for SQLite
        const val TABLE_FAVORITE_LAST: String = "TABLE_FAVORITE_LAST"
        const val TABLE_FAVORITE_NEXT: String = "TABLE_FAVORITE_NEXT"
        const val ID: String = "ID"
        const val EVENT_ID: String = "EVENT_ID"
        const val HOME_ID: String = "HOME_ID"
        const val AWAY_ID: String = "AWAY_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val TIME_EVENT: String = "TIME_EVENT"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val HOME_SCORER: String = "HOME_SCORER"
        const val AWAY_SCORER: String = "AWAY_SCORER"
        const val HOME_GOAL_KEEPER: String = "HOME_GOAL_KEEPER"
        const val AWAY_GOAL_KEEPER: String = "AWAY_GOAL_KEEPER"
        const val HOME_DEFENDER: String = "HOME_DEFENDER"
        const val AWAY_DEFENDER: String = "AWAY_DEFENDER"
        const val HOME_MID_FIELDER: String = "HOME_MID_FIELDER"
        const val AWAY_MID_FIELDER: String = "AWAY_MID_FIELDER"
        const val HOME_FORWARDER: String = "HOME_FORWARDER"
        const val AWAY_FORWARDER: String = "AWAY_FORWARDER"
        const val HOME_YELLOW_CARDS: String = "HOME_YELLOW_CARDS"
        const val AWAY_YELLOW_CARDS: String = "AWAY_YELLOW_CARDS"
        const val HOME_RED_CARDS: String = "HOME_RED_CARDS"
        const val AWAY_RED_CARDS: String = "AWAY_RED_CARDS"
        const val LEAGUE : String = "LEAGUE"
        const val CATEGORY: String = "CATEGORY"


    }
}