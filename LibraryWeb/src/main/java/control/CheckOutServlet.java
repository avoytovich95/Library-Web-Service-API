package control;

import com.fasterxml.jackson.databind.JsonNode;
import utility.Data;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;

/**
 * Created by Alex on 2/23/2018.
 */
public class CheckOutServlet extends HttpServlet{

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{ Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){e.printStackTrace();}
        try (Connection conn = DriverManager.getConnection(
                     Data.getSQL(), Data.getSQLUser(), Data.getSQLPass());
             Statement stmt = conn.createStatement()) {

            JsonNode node = Data.getJSON(request);
            String guest = node.get("guest").asText();//request.getParameter("guest");
            String book = node.get("book").asText();//request.getParameter("book");
            String date = getDate();

            Boolean chkBook = Data.checkBook(stmt, book);
            Boolean chkGuest = Data.checkGuest(stmt, guest);
            Boolean bookAvail = false;
            if (chkBook)
                bookAvail = Data.checkBookAvail(stmt, book);

            request.setAttribute("bookStatus", chkBook);
            request.setAttribute("guestStatus", chkGuest);
            request.setAttribute("bookAvail", bookAvail);

            RequestDispatcher rd = request.getRequestDispatcher("/checkoutresponse");
            if (chkBook && chkGuest && bookAvail) {
                String checkOut = "UPDATE books " +
                        "SET checkedOut = true, " +
                        "outTo = \'" + guest + "\', " +
                        "outAt = \'" + date + "\' " +
                        "WHERE id = " + book;
                stmt.execute(checkOut);

                request.setAttribute("book", Data.getBook(stmt, book));
                request.setAttribute("outTo", Data.getName(stmt, guest));
                request.setAttribute("outDate", date);

            }else if (!bookAvail && chkBook && chkBook){
                request.setAttribute("book", Data.getBook(stmt, book));
            }
            rd.forward(request, response);
        }catch (SQLException | ServletException e){
            e.printStackTrace();
        }
    }

    private String getDate() {
        Calendar cal = Calendar.getInstance();
        int mnth = cal.get(Calendar.MONTH) + 1;
        String month;
        if (mnth < 10)
            month = "0" + mnth;
        else month = mnth + "";

        int dt = cal.get(Calendar.DATE);
        String date;
        if (dt < 10)
            date = "0" + dt;
        else date = dt + "";
        return  month + date + cal.get(Calendar.YEAR);
    }
}
