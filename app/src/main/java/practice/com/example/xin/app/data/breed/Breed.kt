package practice.com.example.xin.app.data.breed

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Breed() {
    var id: String? = null
    var name: String? = null
    var description: String? = null
    var origin: String? = null
    var temperament: String? = null
    var hypoallergenic: Int = 0
    var url: String? = null

    constructor(id: String?,  name: String?,  description: String?,
                 origin: String?,  temperament: String?,  hypoallergenic: Int, url: String?):this(){
        this.id = id
        this.description = description
        this.origin = origin
        this.temperament = temperament
        this.hypoallergenic = hypoallergenic
        this.url = url
    }
    override fun toString(): String = name?: ""


    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "name" to name,
            "description" to description,
            "origin" to origin,
            "temperament" to temperament,
            "hypoallergenic" to hypoallergenic,
            "url" to url
        )
    }

}

