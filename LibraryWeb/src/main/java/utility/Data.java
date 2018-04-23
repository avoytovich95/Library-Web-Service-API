package utility;

import com.fasterxml.jackson.databind.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alex on 2/28/2018.
 */
public class Data {

    public static String getSQL() { return "jdbc:mysql://localhost:3306/librarydb?useSSL=false"; }
    public static String getSQLUser() { return "libraryweb"; }
    public static String getSQLPass() { return ""; }

    public static long getDays(String date)throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
        Date checkout = dateFormat.parse(date);
        Date current = new Date();
        long diff = current.getTime() - checkout.getTime();

        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static boolean checkGuest(Statement stmt, String guest) throws SQLException {
        String gs = "SELECT COUNT(*) FROM guests WHERE id = \'" + guest + "\'";
        ResultSet rs = stmt.executeQuery(gs);
        rs.next();
        return rs.getString("COUNT(*)").equals("1");
    }

    public static boolean checkBook(Statement stmt, String book) throws SQLException {
        String bk = "SELECT COUNT(*) FROM books WHERE id = \'" + book + "\'";
        ResultSet rs = stmt.executeQuery(bk);
        rs.next();
        return rs.getString("COUNT(*)").equals("1");
    }

    public static boolean checkBookAvail(Statement stmt, String book) throws SQLException {
        String av = "SELECT checkedOut FROM books WHERE id = " + book;
        ResultSet rs = stmt.executeQuery(av);
        rs.next();
        return rs.getString("checkedOut").equals("0");
    }

    public static String getName(Statement stmt, String id) throws SQLException {
        String name = "SELECT first, last FROM guests WHERE id = \'" + id + "\'";
        ResultSet rs = stmt.executeQuery(name);
        rs.next();
        return rs.getString("first") + " " + rs.getString("last");
    }

    public static String getBook(Statement stmt, String id) throws SQLException {
        String book = "SELECT title FROM books WHERE id = " + id;
        ResultSet rs = stmt.executeQuery(book);
        rs.next();
        return rs.getString("title");
    }

    public static JsonNode getJSON(HttpServletRequest request) throws IOException {
        StringBuilder string = new StringBuilder();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null)
                string.append(line);
        }catch (Exception e){
            e.printStackTrace();
        }
        ObjectMapper map = new ObjectMapper();
        return map.readTree(string.toString());
    }
}
