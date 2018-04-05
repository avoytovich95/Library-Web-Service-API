package utility

import java.sql.Connection
import java.sql.ResultSet

/**
 * Created by Alex on 3/22/2018.
 */
object GuestsDB {

    // Retrieves all guests from database
    fun getGuests(con: Connection): ResultSet {
        val stmt = con.prepareStatement(
                "SELECT * FROM guests")
        return stmt.executeQuery()
    }

    fun getGuest(id: String, con: Connection): ResultSet {
        val stmt = con.prepareStatement(
                "SELECT * FROM guests WHERE id = ?")
        return stmt.run {
            setString(1, id)
            executeQuery()
        }
    }

    fun getName(id: String, con: Connection): String {
        val rs = getGuest(id, con)
        return rs.run {
            next()
            getString("first") + getString("last")
        }
    }

    // Adds guest to database
    fun addGuest(id: String, first: String, last: String, con: Connection) {
        val stmt = con.prepareStatement(
                "INSERT INTO guests VALUES (?,?,?,?)")
        stmt.run {
            setString(1, id)
            setString(2, first)
            setString(3, last)
            setDouble(4, 0.0)
            execute()
        }
    }

    // Deletes a guest from database
    fun deleteGuest(id: String, con: Connection) {
        val stmt = con.prepareStatement(
                "DELETE FROM guests WHERE id = ?")
        stmt.run {
            setString(1, id)
            execute()
        }
    }

    // Creates an id that does not yet exist in the database
    fun makeID(con: Connection): String {
        val stmt = con.prepareStatement(
                "SELECT COUNT(*) FROM guests WHERE id = ?")
        var rs: ResultSet
        var id: String
        while(true) {
            id = Data.randString(8)
            stmt.setString(1, id)
            rs = stmt.executeQuery()
            rs.next()
            if (rs.getInt("COUNT(*)") == 0)
                return id
        }

    }

    // Checks if id exists in table
    fun checkID(id: String, con: Connection): Boolean {
        val stmt = con.prepareStatement(
                "SELECT COUNT(*) FROM guests WHERE id = ?")
        stmt.setString(1, id)
        val rs = stmt.executeQuery()
        rs.run {
            next()
            return getInt("COUNT(*)") == 1
        }
    }

    // Retrieves fee from guest
    fun getFee(id: String, con: Connection): Double {
        val stmt = con.prepareStatement(
                "SELECT fee FROM guests WHERE id = ?")
        stmt.setString(1, id)
        val rs = stmt.executeQuery()
        rs.run {
            next()
            return getDouble("fee")
        }
    }

    fun updateFee(id: String, fee: Double, con: Connection) {
        val stmt = con.prepareStatement(
                """UPDATE guests
                      SET fee = ?
                      where id = ?""")
        stmt.run {
            setDouble(1, fee)
            setString(2, id)
            execute()
        }
    }
}