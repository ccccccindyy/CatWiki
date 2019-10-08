package practice.com.example.xin.app.data.breed

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import practice.com.example.xin.app.data.DBOpenHelper

class BreedDAO(context: Context): DBOpenHelper(context) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_BREED_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " VARCHAR(4) PRIMARY KEY," +
                COLUMN_NAME
                + " VARCHAR(50)" + ")")


        db.execSQL(CREATE_BREED_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }


    fun addBreed(breed: Breed):Boolean {
        val values = ContentValues()
        values.put(COLUMN_NAME, breed.name)
        values.put(COLUMN_ID, breed.id)
        return super.insert(values,TABLE_NAME)
    }


    fun getBreeds():List<Breed>{
        val breeds:ArrayList<Breed> = ArrayList()
        val selectQuery = "SELECT  * FROM ${TABLE_NAME}"

        var cursor: Cursor? = null

        try{
            cursor = super.read(selectQuery)
        }catch (e: SQLiteException) {
            cursor?.close()
            return ArrayList()
        }

        var id: String
        var name: String
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getString(cursor.getColumnIndex(COLUMN_ID))
                name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val breed= Breed(id = id, name = name)
                breeds.add(breed)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return breeds
    }

    companion object{
        val TABLE_NAME = "breed"
        val COLUMN_ID = "_id"
        val COLUMN_NAME = "name"
    }

}