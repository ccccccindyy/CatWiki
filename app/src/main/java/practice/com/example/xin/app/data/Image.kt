package practice.com.example.xin.app.data

data class Image(val url: String, val id: String) {
    override fun toString(): String = url
}