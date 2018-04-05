package controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.*
import utility.*
import view.*
import java.sql.*

/**
 * Created by Alex on 3/22/2018.
 */
object BookApi {

    fun getBooks(): ArrayNode {
        val map = ObjectMapper()
        try { Class.forName(Api.cls) }
        catch (c: ClassNotFoundException) { c.printStackTrace() }
        return try { val con = DriverManager.getConnection(Api.sql, Api.usr, Api.pas)

            val rs = BooksDB.getBooks(con)
            val books = ArrayList<String>()

            while (rs.next()) {
                books.add(rs.getString("id"))
                books.add(rs.getString("title"))
                books.add(rs.getString("author"))
                books.add(rs.getString("year"))
                books.add(
                        if (rs.getString("checkedOut") == "0") "in"
                        else "out")
            }

            con.close()
            BookView.getBooks(books, map)
        }catch (s: SQLException) {
            s.printStackTrace()
            map.createArrayNode().add("SQL Error")
        }
    }

    fun addBook(body: String): ObjectNode {
        val map = ObjectMapper()
        val tree = map.readTree(body)
        try { Class.forName(Api.cls) }
        catch (c: ClassNotFoundException) { c.printStackTrace() }
        return try { val con = DriverManager.getConnection(Api.sql, Api.usr, Api.pas)

            val title = tree.get("title").asText()
            val author = tree.get("author").asText()
            val year = tree.get("bookYear").asInt()
            val id: Int

            if (IdDB.isEmpty(con)) {
                id = BooksDB.getId(con)
            }else {
                id = IdDB.get(con)
                IdDB.delete(id, con)
            }
            BooksDB.addBook(title, author, year, id, con)

            con.close()
            BookView.addBook(id, title, author, id, map)
        }catch (s: SQLException) {
            s.printStackTrace()
            map.createObjectNode().put("Error", "SQL Error")
        }
    }

    fun deleteBook(body: String): ObjectNode {
        val map = ObjectMapper()
        val tree = map.readTree(body)
        try { Class.forName(Api.cls) }
        catch (c: ClassNotFoundException) { c.printStackTrace() }
        try { val con = DriverManager.getConnection(Api.sql, Api.usr, Api.pas)

            val id = tree.get("id").asInt()
            return if (BooksDB.checkId(id, con)) {
                val rs = BooksDB.getBook(id, con)
                rs.next()
                val title = rs.getString("title")
                val author = rs.getString("author")
                val year = rs.getInt("year")
                BooksDB.deleteBook(id, con)

                con.close()
                BookView.deleteBook(id, title, author, year, map)
            }else {
                con.close()
                map.createObjectNode().put("Error", "invalid book id")
            }

        }catch (s: SQLException) {
            s.printStackTrace()
            return map.createObjectNode().put("Error", "SQL Error")
        }
    }
}