package view

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.sun.org.apache.xpath.internal.operations.Bool

/**
 * Created by Alex on 3/22/2018.
 */
object View {
    fun lateFee(days: Long, weeks: Long, fee: Double, map: ObjectMapper): ObjectNode {
        val node = map.createObjectNode()
        return node.run {
            put("days", days)
            put("weeks", weeks)
            put("fee", fee)
        }
    }

    fun checkIn(book: String, guest: String, date: String, days: Int, weeks: Int, fee: Double, map: ObjectMapper): ObjectNode {
        val node = map.createObjectNode()
        return node.run {
            put("status", "checked in")
            put("book", book)
            put("guest", guest)
            put("date", date)
            put("days", days)
            put("weeks", weeks)
            put("fee", fee)
        }
    }

    fun checkInFail(guest: Boolean, book: Boolean, bookAvail: Boolean, map: ObjectMapper): ObjectNode {
        val node = map.createObjectNode()
        return node.run {
            if (!book) put("bookError", "unknown book id")
            else if (!bookAvail) put("bookError", "book already in")
            if (!guest) put("guestError", "unknown guest id")
            else put("!guestError", "!?!")
        }
    }

    fun checkOut(book: String, guest: String, date: String, map: ObjectMapper): ObjectNode {
        val node = map.createObjectNode()
        return node.run {
            put("status", "checked out")
            put("book", book)
            put("guest", guest)
            put("date", date)
        }
    }

    fun checkOutFail(guest: Boolean, book: Boolean, bookAvail: Boolean, map: ObjectMapper): ObjectNode {
        val node = map.createObjectNode()
        return node.run {
            if (!book) put("bookError", "unknown book id")
            else if (!bookAvail) put("bookError", "book unavailable")
            if (!guest) put("guestError", "unknown guest id")
            else put("!guestError", "!?!")
        }
    }
}