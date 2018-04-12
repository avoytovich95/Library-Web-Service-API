package controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import utility.*
import view.*
import java.sql.*

/**
 * Created by Alex on 3/22/2018.
 */
object Api {
    const val sql = "jdbc:mysql://localhost:3306/librarydb?useSSL=false"
    const val usr = "libraryweb"
    const val pas = "13451460v"
    const val cls = "com.mysql.jdbc.Driver"

    fun lateFee(body: String): ObjectNode {
        val map = ObjectMapper()
        val tree = map.readTree(body)

        val total = Data.getDays(tree.get("date").textValue())
        val weeks = total / 7
        val days = total % 7
        val fee = weeks * 0.25

        return View.lateFee(weeks, days, fee, map)
    }

    fun checkIn(body: String): ObjectNode {
        val map = ObjectMapper()
        val tree = map.readTree(body)
        try { Class.forName(Api.cls) }
        catch (c: ClassNotFoundException) { c.printStackTrace() }
        try { val con = DriverManager.getConnection(Api.sql, Api.usr, Api.pas)

            val guest = tree.get("guest").asText()
            val book = tree.get("book").asInt()

            val chkGuest = GuestsDB.checkID(guest, con)
            val chkBook = BooksDB.checkId(book, con)
            val bookAvail = if (chkBook) BooksDB.checkBookAvail(book, con)
                                else false

            return if (chkGuest && chkBook && bookAvail) {
                val date = BooksDB.getDate(book, con)
                val totalDays = Data.getDays(date)
                val weeks = totalDays.toInt() / 7
                val days = totalDays.toInt() % 7
                val fee = (weeks * 0.25) + GuestsDB.getFee(guest, con)

                BooksDB.checkIn(book, con)
                GuestsDB.updateFee(guest, fee, con)

                val title = BooksDB.getTitle(book, con)
                val name = GuestsDB.getName(guest, con)
                con.close()

                View.checkIn(title, name, date, days, weeks, fee, map)
            }else {
                con.close()
                View.checkInFail(chkGuest, chkBook, bookAvail, map)
            }

        }catch (s: SQLException) {
            s.printStackTrace()
            return map.createObjectNode().put("Error", "SQL Error")
        }
    }

    fun checkOut(body: String): ObjectNode {
        val map = ObjectMapper()
        val tree = map.readTree(body)
        try { Class.forName(Api.cls) }
        catch (c: ClassNotFoundException) { c.printStackTrace() }
        try { val con = DriverManager.getConnection(Api.sql, Api.usr, Api.pas)

            val book = tree.get("book").asInt()
            val guest = tree.get("guest").asText()
            val date = Data.getDate()

            val chkBook = BooksDB.checkId(book, con)
            val chkGuest = GuestsDB.checkID(guest, con)
            val bookAvail = if (chkBook) !BooksDB.checkBookAvail(book, con)
                                else false

            return if (chkBook && chkGuest && bookAvail) {
                val title = BooksDB.getTitle(book, con)
                val name = GuestsDB.getName(guest, con)
                BooksDB.checkOut(guest, book, date, con)

                con.close()
                View.checkOut(title, name, date, map)
            }else {
                con.close()
                View.checkOutFail(chkGuest, chkBook, bookAvail, map)
            }

        }catch (s: SQLException) {
            s.printStackTrace()
            return map.createObjectNode().put("Error", "SQL Error")
        }
    }
}