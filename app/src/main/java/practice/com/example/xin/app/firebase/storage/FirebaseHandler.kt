package practice.com.example.xin.app.firebase.storage

interface FirebaseHandler<T> {

    fun onDataFetched(data: T?)

    fun onDataFetchFailed()
}