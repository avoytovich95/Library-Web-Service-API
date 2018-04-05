package utility

import java.sql.Connection

/**
 * Created by Alex on 3/22/2018.
 */
object IdDB {

    // Checks if id table is empty
    fun isEmpty(con: Connection): Boolean {
        val stmt = con.prepareStatement(
                "SELECT COUNT(*) FROM bookid")
        val rs = stmt.executeQuery()
        rs.run {
            next()
            return getInt("COUNT(*)") == 0
        }
    }

    // Deletes id from table
    fun delete(id: Int, con: Connection) {
        val stmt = con.prepareStatement(
                "DELETE FROM bookid WHERE id = ?")
        stmt.setInt(1, id)
        stmt.execute()
    }

    //Gets first available id from table
    fun get(con: Connection): Int {
        val stmt = con.prepareStatement(
                "SELECT * FROM bookid")
        val rs = stmt.executeQuery()
        rs.run {
            next()
            return getInt("id")
        }
    }
}