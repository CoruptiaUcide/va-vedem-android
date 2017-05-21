package com.coruptiaucide.vavedem.api.database

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

/**
 * Created by tiberiugolaes on 21/05/2017.
 */
internal abstract class DatabaseManager<T> {
    val tableName: String
    val columns: Array<Column>

    class Column(val name: String, val type: String)

    constructor(tableName: String, columns: Array<Column>) {
        this.tableName = tableName
        this.columns = columns
    }

    open fun init(db: SQLiteDatabase) {
        db.execSQL(generateCreateTableStatement(tableName, columns))
    }

    protected open fun generateCreateTableStatement(table: String, columns: Array<Column>): String {
        var query = "CREATE TABLE IF NOT EXISTS $table (id INT PRIMARY KEY"
        for (column in columns) {
            query += ", ${column.name} ${column.type}"
        }
        return query + ")"
    }


    open fun save(db: SQLiteDatabase, fk: Long? = null, t: T): Long =
            db.insertWithOnConflict(tableName, fk?.toString(), toContentValues(t, fk), SQLiteDatabase.CONFLICT_REPLACE)

    protected abstract fun toContentValues(t: T, fk: Long? = null): ContentValues
    protected abstract fun get(cursor: Cursor): T

    open fun get(db: SQLiteDatabase, where: String? = null, selectionArgs: Array<out String>? = null, orderBy: String? = null, limit: Int? = null, items: MutableList<T>): List<T> {
        var query = "SELECT * FROM $tableName"
        if (where != null) query += " WHERE $where"
        if (orderBy != null) query += " ORDER BY $orderBy"
        if (limit != null && limit > -1) query += " LIMIT $limit"
        val cursor = db.rawQuery(query, selectionArgs)
        if (cursor.moveToFirst() && cursor.count >= 1) {
            do {
                val item = get(cursor)
                items.add(item)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return items
    }

    open fun save(db: SQLiteDatabase, fk: Long? = null, items: List<T>) {
        db.doInTransaction {
            for (item in items) {
                save(db, fk, item)
            }
        }
    }

    fun delete(db: SQLiteDatabase, where: String? = null, whereArgs: Array<out String>? = null): Int
            = db.delete(tableName, where, whereArgs)

}

