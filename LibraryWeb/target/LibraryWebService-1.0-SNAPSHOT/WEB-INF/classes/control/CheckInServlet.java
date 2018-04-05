package control;

import com.fasterxml.jackson.databind.JsonNode;
import utility.Data;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;

/**
 * Created by Alex on 3/8/2018.
 */
public class CheckInServlet extends HttpServlet{

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try { Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) { e.printStackTrace(); }
        try (Connection conn = DriverManager.getConnection(
                Data.getSQL(), Data.getSQLUser(), Data.getSQLPass());
             Statement stmt = conn.createStatement()) {

            JsonNode node = Data.getJSON(request);
            String guest = node.get("guest").asText();//request.getParameter("guest");
            String book = node.get("book").asText();//request.getParameter("book");

            Boolean chkGuest = Data.checkGuest(stmt, guest);
            Boolean chkBook = Data.checkBook(stmt, book);
            Boolean bookAvail = false;
            if (chkBook)
                bookAvail = !Data.checkBookAvail(stmt, book);

            request.setAttribute("bookStatus", chkBook);
            request.setAttribute("guestStatus", chkGuest);
            request.setAttribute("bookAvail", bookAvail);
            System.out.println(chkBook + " " + chkGuest + " " + bookAvail);

            RequestDispatcher rd = request.getRequestDispatcher("/checkinresponse");
            if(chkBook && chkGuest && bookAvail) {
                String date = getDate(stmt, book);
                long days = Data.getDays(date);
                int week = (int)days / 7;
                int day = (int)days % 7;
                double fee = (week * 0.25) + getFee(stmt, guest);

                String checkIn = "UPDATE books " +
                        "SET checkedOut = false, " +
                        "outTo = \'\', " +
                        "outAt = \'\' " +
                        "WHERE id = " + book;
                stmt.execute(checkIn);
                String updateGuest = "UPDATE guests " +
                        "SET fee = " + fee + " " +
                        "WHERE id = \'" + guest + "\'";
                stmt.execute(updateGuest);

                request.setAttribute("book", Data.getBook(stmt, book));
                request.setAttribute("guest", Data.getName(stmt, guest));
                request.setAttribute("date", date);
                request.setAttribute("weeks", week);
                request.setAttribute("days", day);
                request.setAttribute("fee", fee);
            }
            rd.forward(request ,response);
        } catch (SQLException | ServletException | ParseException e) {
            e.printStackTrace();
        }
    }

    private String getDate(Statement stmt, String book) throws SQLException{
        String date = "SELECT outAt FROM books WHERE id = " + book;
        ResultSet rs = stmt.executeQuery(date);
        rs.next();
        return rs.getString("outAt");
    }

    private double getFee(Statement stmt, String guest) throws SQLException {
        String fee = "SELECT fee FROM guests WHERE id = \'" + guest + "\'";
        ResultSet rs = stmt.executeQuery(fee);
        rs.next();
        return Double.parseDouble(rs.getString("fee"));
    }
}
