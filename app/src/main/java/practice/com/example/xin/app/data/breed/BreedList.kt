package practice.com.example.xin.app.data.breed

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties


class BreedList() {
    private var breeds: HashMap<String,Breed> = hashMapOf()

    constructor( breeds: HashMap<String,Breed>):this() {
        this.breeds = breeds
    }

    fun getBreedList(): ArrayList<Breed> {
      val breedList =  arrayListOf<Breed>()
        breedList.addAll(breeds.values)
        return breedList
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "breeds" to breeds
        )
    }
}

