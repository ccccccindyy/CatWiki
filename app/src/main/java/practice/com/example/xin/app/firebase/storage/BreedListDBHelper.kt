package practice.com.example.xin.app.firebase.storage

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import practice.com.example.xin.app.data.breed.BreedList
import com.google.firebase.database.ValueEventListener
import practice.com.example.xin.app.data.breed.Breed
import practice.com.example.xin.app.firebase.ReadTimeDBHelper
import java.lang.Exception

class BreedListDBHelper: ReadTimeDBHelper<ArrayList<Breed>>() {
    init {
       dataListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val breedList = arrayListOf<Breed>()
               for( postdataSnapshot in dataSnapshot.child("breeds").children ){
                   breedList.add(postdataSnapshot.getValue(Breed::class.java)?: Breed())
               }
                firebaseHandler?.onDataFetched(breedList)
            }


            override fun onCancelled(databaseError: DatabaseError) {
                firebaseHandler?.onDataFetchFailed()
            }
        }
    }
}
