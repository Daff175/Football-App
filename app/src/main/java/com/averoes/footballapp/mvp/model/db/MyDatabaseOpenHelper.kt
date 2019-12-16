package com.averoes.footballapp.mvp.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.averoes.footballapp.mvp.model.Favorite
import com.averoes.footballapp.mvp.model.FavoriteTeam
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, "favorite.db", null, 2) {

    companion object {
        private var instace: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instace == null) {
                instace = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instace as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {

        db?.createTable(
            Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.EVENT_ID to TEXT + UNIQUE,
            Favorite.EVENT_NAME to TEXT,
            Favorite.EVENT_DATE to TEXT,
            Favorite.HOME_TEAM_ID to TEXT,
            Favorite.HOME_TEAM_NAME to TEXT,
            Favorite.HOME_TEAM_SCORE to TEXT,
            Favorite.AWAY_TEAM_ID to TEXT,
            Favorite.AWAY_TEAM_NAME to TEXT,
            Favorite.AWAY_TEAM_SCORE to TEXT,
            Favorite.CARD_HOME to TEXT,
            Favorite.CARD_AWAY to TEXT,
            Favorite.GOAL_HOME to TEXT,
            Favorite.GOAL_AWAY to TEXT


        )

        db?.createTable(
            FavoriteTeam.TABLE_TEAM, true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
            FavoriteTeam.TEAM_NAME to TEXT,
            FavoriteTeam.TEAM_DESC to TEXT,
            FavoriteTeam.TEAM_BANNER to TEXT,
            FavoriteTeam.TEAM_POSTER to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.dropTable(Favorite.TABLE_FAVORITE, true)
        db?.dropTable(FavoriteTeam.TABLE_TEAM, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)