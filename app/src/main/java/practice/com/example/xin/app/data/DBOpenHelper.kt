package practice.com.example.xin.app.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

abstract class DBOpenHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    fun insert(values: ContentValues, tableName: String): Boolean {
        val db = this.writableDatabase
        val success = db.insert(tableName, null, values)
        db.close()
        return success > 0
    }

    fun read(selectQuery: String): Cursor {
        val db = this.readableDatabase
        return db.rawQuery(selectQuery, null)
    }


    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "cat.db"
    }

}