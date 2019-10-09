package practice.com.example.xin.app.data.breed

import android.os.Parcel
import android.os.Parcelable

data class Breed(val id: String?, val name: String?, val description: String?,
                 val origin: String?, val temperament: String?, val hypoallergenic: Int) {

    override fun toString(): String = name?: ""
}

