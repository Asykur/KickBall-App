package com.asykurkhamid.kickball.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataFavoriteNext(val id: Int?,
                            val eventID                   : String?,
                            val homeID                    : String?,
                            val awayID                    : String?,
                            val homeTeamName              : String?,
                            val awayTeamName              : String?,
                            val homeScore                 : String?,
                            val awayScore                 : String?,
                            val dateEvent                 : String?,
                            val timeEvent                 : String?,
                            val strLeague                 : String?,
                            val category                  : String?
) : Parcelable
