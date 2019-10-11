package practice.com.example.xin.app.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBOpenHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_BREED_TABLE = ("CREATE TABLE " +
                DataUtil.TABLE_NAME + "(" +
                DataUtil.COLUMN_ID + " VARCHAR(4) PRIMARY KEY," +
                DataUtil.COLUMN_NAME + " VARCHAR(50)," +
                DataUtil.COLUMN_DESC + " VARCHAR(500)," +
                DataUtil.COLUMN_ORIGIN + " VARCHAR(50)," +
                DataUtil.COLUMN_TEMP + " VARCHAR(280)," +
                DataUtil.COLUMN_HYPO + " INTEGER"
                + ")"
                )

        db?.execSQL(CREATE_BREED_TABLE)

    }

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
        val success =
            db.insertWithOnConflict(tableName, null, values, SQLiteDatabase.CONFLICT_REPLACE)
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