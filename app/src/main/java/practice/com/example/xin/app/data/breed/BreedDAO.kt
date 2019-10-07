package practice.com.example.xin.app.data.breed

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import practice.com.example.xin.app.data.DBOpenHelper

class BreedDAO(context: Context) {
    val dbOpenHelper = DBOpenHelper(context)
    val TABLE_NAME = "breed"
    val COLUMN_ID = "_id"
    val COLUMN_NAME = "name"

    fun addBreed(breed: Breed):Boolean {
        val values = ContentValues()
        values.put(COLUMN_NAME, breed.name)
        values.put(COLUMN_ID, breed.id)
        return dbOpenHelper.insert(values,TABLE_NAME)
    }


    fun getBreeds():List<Breed>{
        val breeds:ArrayList<Breed> = ArrayList()
        val selectQuery = "SELECT  * FROM ${TABLE_NAME}"

        var cursor: Cursor? = null

        try{
            cursor = dbOpenHelper.read(selectQuery)
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
}