package practice.com.example.xin.app.firebase.storage

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import practice.com.example.xin.app.data.breed.Breed

class BreedHelper: ReadTimeDBHelper<Breed>(){
    init {
        dataListener = object : ValueEventListener {


            override fun onCancelled(databaseError: DatabaseError) {
                firebaseHandler?.onDataFetchFailed()
            }


            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.getValue(Breed::class.java)?.let {
                    firebaseHandler?.onDataFetched(it)
                }
            }
        }
    }
}
