package practice.com.example.xin.app.data

data class Cat(val id: String, val name: String, val details: String, val homeTown: String) {
    override fun toString(): String = name
}