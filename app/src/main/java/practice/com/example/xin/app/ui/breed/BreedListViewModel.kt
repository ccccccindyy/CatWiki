package practice.com.example.xin.app.ui.breed

import androidx.lifecycle.ViewModel
import practice.com.example.xin.app.data.breed.Breed
import practice.com.example.xin.app.data.breed.BreedList
import practice.com.example.xin.app.firebase.storage.BreedListDBHelper
import practice.com.example.xin.app.firebase.storage.FirebaseHandler
import javax.inject.Inject


class BreedListViewModel @Inject constructor(private val breedListDBHelper: BreedListDBHelper): ViewModel() {

    fun initRealTimeDB(firebaseHandler: FirebaseHandler<ArrayList<Breed>>){
        breedListDBHelper.firebaseHandler = firebaseHandler
    }

    fun initBreedData(){
        breedListDBHelper.addValueListener()
    }
}