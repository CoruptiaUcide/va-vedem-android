package com.coruptiaucide.vavedem.api.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.util.*

/**
 * Created by tiberiugolaes on 21/05/2017.
 */
fun Cursor.getString(columnName: String): String? {
    return getString(getColumnIndex(columnName))
}

fun Cursor.getLong(columnName: String): Long {
    return getLong(getColumnIndex(columnName))
}

fun Cursor.getInt(columnName: String): Int {
    return getInt(getColumnIndex(columnName))
}

fun Cursor.getDouble(columnName: String): Double {
    return getDouble(getColumnIndex(columnName))
}
//
//fun Cursor.getFloat(columnName: String): Float {
//    return getFloat(getColumnIndex(columnName))
//}

fun Cursor.getDate(columnName: String): Date? {
    val long = getLong(columnName)
    return if (long == 0L) null else Date(long)
}

internal inline fun dbTask(db: SQLiteDatabase = VaVedemSQLiteHelper.instance.writableDatabase, dbTask: (SQLiteDatabase) -> Unit) {
    try {
        dbTask(db)
    } catch (e: Exception) {
        Log.e("SQLite", "some serious exception", e)
    } finally {
//        db.close()
    }
}

internal inline fun SQLiteDatabase.doInTransaction(dbTask: SQLiteDatabase.() -> Unit) {
    beginTransaction()
    try {
        dbTask()
        setTransactionSuccessful()
    } catch (e: Exception) {
        Log.w("SQLite", e)
    } finally {
        endTransaction()
    }
}
