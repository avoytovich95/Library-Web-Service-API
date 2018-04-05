package controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.*
import utility.*
import view.*
import java.sql.*

/**
 * Created by Alex on 3/22/2018.
 */
object GuestApi {

    fun getGuests(): ArrayNode {
        val map = ObjectMapper()
        try { Class.forName(Api.cls) }
        catch (c: ClassNotFoundException) { c.printStackTrace() }
        return try { val con = DriverManager.getConnection(Api.sql, Api.usr, Api.pas)

            val rs = GuestsDB.getGuests(con)
            val guests = ArrayList<String>()

            rs.run {
                while (next()) {
                    guests.add(getString("id"))
                    guests.add(getString("first"))
                    guests.add(getString("last"))
                }
            }
            con.close()

            GuestView.getGuests(guests, map)
        }catch (s: SQLException) {
            s.printStackTrace()
            map.createArrayNode().add("SQL Error")
        }
    }

    fun addGuest(body: String): ObjectNode {
        val map = ObjectMapper()
        val tree = map.readTree(body)
        try { Class.forName(Api.cls) }
        catch (c: ClassNotFoundException) { c.printStackTrace() }
        return try { val con = DriverManager.getConnection(Api.sql, Api.usr, Api.pas)

            val first = tree.get("first").asText()
            val last = tree.get("last").asText()
            val id = GuestsDB.makeID(con)

            GuestsDB.addGuest(id, first, last, con)
            con.close()

            GuestView.addGuest(id, first, last, map)
        }catch (s: SQLException) {
            s.printStackTrace()
            map.createObjectNode().put("Error", "SQL Error")
        }
    }

    fun deleteGuest(body: String): ObjectNode {
        val map = ObjectMapper()
        val tree = map.readTree(body)
        try { Class.forName(Api.cls) }
        catch (c: ClassNotFoundException) { c.printStackTrace() }
        try {val con = DriverManager.getConnection(Api.sql, Api.usr, Api.pas)

            val id = tree.get("id").asText()
            return if (GuestsDB.checkID(id, con)) {
                val rs = GuestsDB.getGuest(id, con)
                rs.next()
                val first = rs.getString("first")
                val last = rs.getString("last")
                GuestsDB.deleteGuest(id, con)
                con.close()

                GuestView.deleteGuest(id, first, last, map)
            }else {
                con.close()
                map.createObjectNode().put("Error", "invalid guest id")
            }

        }catch (s: SQLException) {
            s.printStackTrace()
            return map.createObjectNode().put("ERROR", "SQL Error")
        }
    }
}