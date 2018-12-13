package com.asykurkhamid.kickball.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.asykurkhamid.kickball.pojo.DataFavorite
import org.jetbrains.anko.db.*

class DBOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: DBOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DBOpenHelper {
            if (instance == null) {
                instance = DBOpenHelper(ctx.applicationContext)
            }
            return instance as DBOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(DataFavorite.TABLE_FAVORITE_LAST, true,
                DataFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DataFavorite.EVENT_ID to TEXT + UNIQUE,
                DataFavorite.HOME_ID to TEXT,
                DataFavorite.AWAY_ID to TEXT,
                DataFavorite.HOME_TEAM_NAME to TEXT,
                DataFavorite.AWAY_TEAM_NAME to TEXT,
                DataFavorite.HOME_SCORE to TEXT,
                DataFavorite.AWAY_SCORE to TEXT,
                DataFavorite.DATE_EVENT to TEXT,
                DataFavorite.TIME_EVENT to TEXT,
                DataFavorite.HOME_SCORER to TEXT,
                DataFavorite.AWAY_SCORER to TEXT,
                DataFavorite.HOME_GOAL_KEEPER to TEXT,
                DataFavorite.AWAY_GOAL_KEEPER to TEXT,
                DataFavorite.HOME_DEFENDER to TEXT,
                DataFavorite.AWAY_DEFENDER to TEXT,
                DataFavorite.HOME_MID_FIELDER to TEXT,
                DataFavorite.AWAY_MID_FIELDER to TEXT,
                DataFavorite.HOME_FORWARDER to TEXT,
                DataFavorite.AWAY_FORWARDER to TEXT,
                DataFavorite.HOME_YELLOW_CARDS to TEXT,
                DataFavorite.AWAY_YELLOW_CARDS to TEXT,
                DataFavorite.HOME_RED_CARDS to TEXT,
                DataFavorite.AWAY_RED_CARDS to TEXT,
                DataFavorite.LEAGUE to TEXT,
                DataFavorite.CATEGORY to TEXT)

        db?.createTable(DataFavorite.TABLE_FAVORITE_NEXT, true,
                DataFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DataFavorite.EVENT_ID to TEXT + UNIQUE,
                DataFavorite.HOME_ID to TEXT,
                DataFavorite.AWAY_ID to TEXT,
                DataFavorite.HOME_TEAM_NAME to TEXT,
                DataFavorite.AWAY_TEAM_NAME to TEXT,
                DataFavorite.HOME_SCORE to TEXT,
                DataFavorite.AWAY_SCORE to TEXT,
                DataFavorite.DATE_EVENT to TEXT,
                DataFavorite.TIME_EVENT to TEXT,
                DataFavorite.LEAGUE to TEXT,
                DataFavorite.CATEGORY to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(DataFavorite.TABLE_FAVORITE_LAST, true)
    }

}

val Context.database: DBOpenHelper
    get() = DBOpenHelper.getInstance(applicationContext)
