package practice.com.example.xin.app.dummy

import practice.com.example.xin.app.data.Cat
import java.util.ArrayList
import java.util.HashMap

object Content {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<Cat> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, Cat> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(
                createCat(
                    i
                )
            )
        }
    }

    private fun addItem(item: Cat) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createCat(position: Int): Cat {
        return Cat(
            position.toString(),
            "Item " + position,
            makeDetails(position),
            ""
        )
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }
}
