package practice.com.example.xin.app.data.breed

data class Breed(val id: String, val name: String) {
    override fun toString(): String = name
}