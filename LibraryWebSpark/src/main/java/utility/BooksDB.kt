package utility

import java.sql.*

/**
 * Created by Alex on 3/22/2018.
 */
object BooksDB {

    // Adds book to database
    fun addBook(title: String, author: String, year: Int, id: Int, con: Connection) {
        val stmt = con.prepareStatement(
                "INSERT INTO books VALUES (?,?,?,?,?,?,?)")
        stmt.run {
            setInt(1, id)
            setString(2, title)
            setString(3, author)
            setInt(4, year)
            setBoolean(5, false)
            setString(6, "")
            setString(7, "")
            execute()
        }
    }

    // Retrieves all books in database
    fun getBooks(con: Connection): ResultSet {
        val stmt = con.prepareStatement(
                "SELECT * FROM books")
        return stmt.executeQuery()
    }

    // Retrieves book from database by id
    fun getBook(id: Int, con: Connection): ResultSet {
        val stmt = con.prepareStatement(
                "SELECT * FROM books WHERE id = ?")
        stmt.setInt(1, id)
        return stmt.executeQuery()
    }

    // Gets book title from database by id
    fun getTitle(id: Int, con: Connection): String {
        val rs = getBook(id, con)
        return rs.run {
            next()
            getString("title")
        }
    }

    // Deletes book from database by id
    fun deleteBook(id: Int, con: Connection) {
        val stmt = con.prepareStatement(
                "DELETE FROM books WHERE id = ?")
        stmt.run {
            setInt(1, id)
            execute()
        }
    }

    // Gets next id from database to assign to new book
    fun getId(con: Connection): Int {
        val stmt = con.prepareStatement(
                "SELECT COUNT(*) FROM books")
        val rs = stmt.executeQuery()
        rs.run {
            next()
            return getInt("COUNT(*)") + 1000
        }
    }

    // Checks if book is valid
    fun checkId(id: Int, con: Connection): Boolean {
        val stmt = con.prepareStatement(
                "SELECT COUNT(*) FROM books WHERE id = ?")
        stmt.setInt(1, id)
        val rs = stmt.executeQuery()
        rs.run {
            next()
            return getInt("COUNT(*)") == 1
        }
    }

    // Checks if book is available for check out
    fun checkBookAvail(id: Int, con: Connection): Boolean {
        val stmt = con.prepareStatement(
                "SELECT checkedOut FROM books WHERE id = ?")
        stmt.setInt(1, id)
        val rs = stmt.executeQuery()
        rs.run {
            next()
            return getBoolean("checkedOut")
        }
    }

    // Gets books check out date
    fun getDate(id: Int, con: Connection): String {
        val stmt = con.prepareStatement(
                "SELECT outAt FROM books WHERE id = ?")
        stmt.setInt(1, id)
        val rs = stmt.executeQuery()
        rs.run {
            next()
            return getString("outAt")
        }
    }

    //
    fun checkOut(guest: String, book: Int, date: String, con: Connection) {
        val stmt = con.prepareStatement(
                """UPDATE books
                    SET checkedOut = TRUE,
                    outTo = ?,
                    outAt = ?
                    WHERE id = ?""")
        stmt.run {
            setString(1, guest)
            setString(2, date)
            setInt(3, book)
            execute()
        }
    }

    fun checkIn(id: Int, con: Connection) {
        val stmt = con.prepareStatement(
                """UPDATE books
                      SET checkedOut = FALSE,
                          outTo = ?,
                          outAt = ?
                      WHERE id = ?""")
        stmt.run {
            setString(1, "")
            setString(2, "")
            setInt(3, id)
            execute()
        }
    }
}