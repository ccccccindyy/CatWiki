package practice.com.example.xin.app.firebase.storage

import com.google.firebase.database.*


abstract class ReadTimeDBHelper<T>() {
    private var database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private var child: String = ""
    var firebaseHandler: FirebaseHandler<T>? = null

    lateinit var dataListener: ValueEventListener
    lateinit var childEventListener: ChildEventListener


    fun addValueListener(){
        database.addValueEventListener(dataListener)
    }

    fun addValueListener(vararg children: String){
        var dbref = database
        for(child in children) {
            dbref = dbref.child(child)
        }
        dbref.addValueEventListener(dataListener)
    }

}