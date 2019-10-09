package practice.com.example.xin.app.data.breed

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import practice.com.example.xin.app.data.DBOpenHelper
import practice.com.example.xin.app.data.DataUtil.Companion.COLUMN_DESC
import practice.com.example.xin.app.data.DataUtil.Companion.COLUMN_HYPO
import practice.com.example.xin.app.data.DataUtil.Companion.COLUMN_ID
import practice.com.example.xin.app.data.DataUtil.Companion.COLUMN_NAME
import practice.com.example.xin.app.data.DataUtil.Companion.COLUMN_ORIGIN
import practice.com.example.xin.app.data.DataUtil.Companion.COLUMN_TEMP
import practice.com.example.xin.app.data.DataUtil.Companion.TABLE_NAME
import javax.inject.Inject

class BreedDAO @Inject constructor(val dbOpenHelper: DBOpenHelper){


    fun addBreed(breed: Breed):Boolean {
        val values = ContentValues()
        values.put(COLUMN_NAME, breed.name)
        values.put(COLUMN_ID, breed.id)
        values.put(COLUMN_DESC, breed.description)
        values.put(COLUMN_TEMP, breed.temperament)
        values.put(COLUMN_ORIGIN, breed.origin)
        values.put(COLUMN_HYPO, breed.hypoallergenic)
        return dbOpenHelper.insert(values,TABLE_NAME)
    }


    fun getBreeds():List<Breed>{
        val breeds:ArrayList<Breed> = ArrayList()
        val selectQuery = "SELECT  * FROM $TABLE_NAME"

        var cursor: Cursor? = null

        try{
            cursor = dbOpenHelper.read(selectQuery)
        }catch (e: SQLiteException) {
            cursor?.close()
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
          do {
                breeds.add(getBreed(cursor))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return breeds
    }

    fun getBreed(id: String): Breed?{
        val selectQuery = "SELECT  * FROM $TABLE_NAME where $COLUMN_ID = '$id'"
        var cursor: Cursor? = null
        try{
            cursor = dbOpenHelper.read(selectQuery)
        }catch (e: SQLiteException) {
            cursor?.close()
            return null
        }

        val breed = if(cursor.moveToFirst()) getBreed(cursor) else null
        cursor.close()
        return breed
    }

    private fun getBreed(cursor: Cursor): Breed {
        return  Breed(id = cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
            name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
            description = cursor.getString(cursor.getColumnIndex(COLUMN_DESC)),
            origin = cursor.getString(cursor.getColumnIndex(COLUMN_ORIGIN)),
            temperament = cursor.getString(cursor.getColumnIndex(COLUMN_TEMP)),
            hypoallergenic = cursor.getInt(cursor.getColumnIndex(COLUMN_HYPO)))
    }

}