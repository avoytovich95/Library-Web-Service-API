package view

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.*

/**
 * Created by Alex on 3/22/2018.
 */
object BookView {

    fun getBooks(books: ArrayList<String>, map: ObjectMapper): ArrayNode {
        val array = map.createArrayNode()
        var node: ObjectNode
        for (i in 0 until books.size step 5) {
            node = map.createObjectNode()
            array.add( node.run {
                put("id", books[i])
                put("title", books[i+1])
                put("author", books[i+2])
                put("year", books[i+3])
                put("status", books[i+4])
            } )
        }
        return array
    }

    fun addBook(id: Int, title: String, author: String, year: Int, map: ObjectMapper): ObjectNode {
        val node = map.createObjectNode()
        return node.run {
            put("id", id)
            put("title", title)
            put("author", author)
            put("year", year)
        }
    }

    fun deleteBook(id: Int, title: String, author: String, year: Int, map: ObjectMapper): ObjectNode {
        val node = map.createObjectNode()
        return node.run {
            put("status", "deleted")
            put("id", id)
            put("title", title)
            put("author", author)
            put("year", year)
        }
    }
}