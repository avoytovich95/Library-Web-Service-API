package view

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode

/**
 * Created by Alex on 3/22/2018.
 */
object GuestView {

    fun getGuests(guests: ArrayList<String>, map: ObjectMapper): ArrayNode {
        val array = map.createArrayNode()
        var node: ObjectNode
        for (i in 0 until guests.size step 3) {
            node = map.createObjectNode()
            array.add( node.run {
                node.put("id", guests[i])
                node.put("first", guests[i+1])
                node.put("last", guests[i+2])
            } )
        }
        return array
    }

    fun addGuest(id: String, first: String, last: String, map: ObjectMapper): ObjectNode {
        val node = map.createObjectNode()
        return node.run {
            put("id", id)
            put("first", first)
            put("last", last)
        }
    }

    fun deleteGuest(id: String, first: String, last: String, map: ObjectMapper): ObjectNode {
        val node = map.createObjectNode()
        return node.run {
            put("status", "deleted")
            put("id", id)
            put("first", first)
            put("last", last)
        }
    }
}