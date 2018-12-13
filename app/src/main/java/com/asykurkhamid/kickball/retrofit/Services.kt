package com.asykurkhamid.kickball.retrofit

import com.asykurkhamid.kickball.pojo.DataAllTeams
import com.asykurkhamid.kickball.pojo.DataLastMatch
import com.asykurkhamid.kickball.pojo.DataNextMatch
import com.asykurkhamid.kickball.pojo.DataTeams
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {
    @GET("eventspastleague.php")
    fun getLastData(@Query("id") leagueID: String?): Call<DataLastMatch>

    @GET("eventsnextleague.php")
    fun getNextData(@Query("id") leagueID: String?): Call<DataNextMatch>

    @GET("lookupteam.php")
    fun getTeamData(@Query("id") teamID: String?): Call<DataTeams>

    @GET("search_all_teams.php")
    fun getAllTeamData(@Query("l") teamID: String?): Call<DataAllTeams>

    @GET("searchevents.php")
    fun getFIlteredMatch(@Query("e") teamID: String?,
                         @Query("query") query : String?
    ): Call<DataAllTeams>


}