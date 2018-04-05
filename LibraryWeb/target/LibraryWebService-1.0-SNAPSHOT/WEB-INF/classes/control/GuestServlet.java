package control;

import com.fasterxml.jackson.databind.JsonNode;
import utility.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;


/**
 * Created by Alex on 2/23/2018.
 */
public class GuestServlet extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){e.printStackTrace();}
        try (Connection conn = DriverManager.getConnection(
                Data.getSQL(), Data.getSQLUser(), Data.getSQLPass());
                Statement stmt = conn.createStatement()) {

            JsonNode node = Data.getJSON(request);
            String first = node.get("first").asText();//request.getParameter("first");
            String last = node.get("last").asText();//request.getParameter("last");
            String id = makeID(stmt);

            PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO guests VALUES(?,?,?,?)");
            st.setString(1, id);
            st.setString(2, first);
            st.setString(3, last);
            st.setDouble(4, 0.0);
            st.execute();

            request.setAttribute("id", id);
            request.setAttribute("first", first);
            request.setAttribute("last", last);
            RequestDispatcher rd = request.getRequestDispatcher("/guestresponse");
            rd.forward(request, response);

        }catch(SQLException | ServletException e){
            e.printStackTrace();
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e) {e.printStackTrace(); }
        try (Connection conn = DriverManager.getConnection(
                Data.getSQL(), Data.getSQLUser(), Data.getSQLPass());
             Statement stmt = conn.createStatement()) {

            JsonNode node = Data.getJSON(request);
            String id = node.get("id").asText();//request.getParameter("id");

            boolean chkGuest = checkGuest(stmt, id);
            request.setAttribute("check", chkGuest);
            if (chkGuest) {
                String[] guest = getGuest(stmt, id);
                request.setAttribute("id", id);
                request.setAttribute("first", guest[0]);
                request.setAttribute("last", guest[1]);

                String delete = "DELETE FROM guests WHERE id = \'" + id + "\'";
                stmt.execute(delete);
                RequestDispatcher rd = request.getRequestDispatcher("/guestresponse");
                rd.forward(request, response);
            }
        }catch (SQLException | ServletException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e) { e.printStackTrace(); }
        try (Connection conn = DriverManager.getConnection(
                Data.getSQL(), Data.getSQLUser(), Data.getSQLPass());
             Statement stmt = conn.createStatement()) {

            ArrayList<String> guestList = new ArrayList<>();
            String guests = "SELECT * FROM guests";
            ResultSet rs = stmt.executeQuery(guests);

            while(rs.next()) {
                guestList.add(rs.getString("id"));
                guestList.add(rs.getString("first"));
                guestList.add(rs.getString("last"));
            }
            request.setAttribute("guests", guestList);
            RequestDispatcher rd = request.getRequestDispatcher("/guestresponse");
            rd.forward(request, response);

        } catch (SQLException | ServletException e) {
            e.printStackTrace();
        }

    }

    private String makeID(Statement stmt)throws SQLException{
        String id = SimpleRandomID.nextString(8);
        ResultSet rs;
        String sql = "SELECT COUNT(*) FROM guests WHERE id = \'" + id + "\'";
        while(true) {
            rs = stmt.executeQuery(sql);
            rs.next();
            if (rs.getString("Count(*)").equals("1"))
                id = SimpleRandomID.nextString(8);
            else return id;
        }
    }

    private boolean checkGuest(Statement stmt, String guest) throws SQLException {
        String gs = "SELECT COUNT(*) FROM guests WHERE id = \'" + guest + "\'";
        ResultSet rs = stmt.executeQuery(gs);
        rs.next();
        return rs.getString("COUNT(*)").equals("1");
    }

    private String[] getGuest(Statement stmt, String id) throws SQLException{
        String name = "SELECT first, last FROM guests WHERE id = \'" + id + "\'";
        ResultSet rs = stmt.executeQuery(name);
        rs.next();
        String[] guest = new String[3];
        guest[0] = rs.getString("first");
        guest[1] = rs.getString("last");
        return guest;
    }

}
