package com.coruptiaucide.vavedem.api.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.coruptiaucide.vavedem.api.Api

/**
 * Created by tiberiugolaes on 21/05/2017.
 */
private const val DATABASE_NAME = "vavedem.db"
private const val VER_1 = 1// 5.0
private const val DATABASE_VERSION = VER_1

internal const val TABLE_PRIMARII = "Primarii"


internal class VaVedemSQLiteHelper private constructor(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        val instance: VaVedemSQLiteHelper by lazy { VaVedemSQLiteHelper(Api.mContext) }
    }

    override fun onCreate(db: SQLiteDatabase) {
        PrimariiDatabaseManager.init(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }

    fun clear(db: SQLiteDatabase) {
        db.delete(TABLE_PRIMARII, null, null)
    }
}
