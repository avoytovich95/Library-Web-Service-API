package control;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import utility.Data;

/**
 * Created by Alex on 2/15/2018.
 */
public class BookServlet extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e){e.printStackTrace(); }
        try (Connection conn = DriverManager.getConnection(
                Data.getSQL(), Data.getSQLUser(), Data.getSQLPass());
                Statement stmt = conn.createStatement()) {

            JsonNode node = Data.getJSON(request);
            String title = node.get("title").asText();
            String author = node.get("author").asText();
            int bookYear = node.get("bookYear").asInt();

            int id;
            if (checkIDTable(stmt)) {
                id = nextID(stmt);
                String deleteID = "DELETE FROM bookid WHERE id = " + id;
                stmt.execute(deleteID);
            }
            else id = getID(stmt);

            PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO books VALUES (?,?,?,?,?,?,?)");
            st.setInt(1, id);
            st.setString(2, title);
            st.setString(3, author);
            st.setInt(4, bookYear);
            st.setBoolean(5, false);
            st.setString(6, "");
            st.setString(7, "");
            System.out.println(st.toString());
            st.execute();

            request.setAttribute("id", id);
            request.setAttribute("title", title);
            request.setAttribute("author", author);
            request.setAttribute("year", bookYear);
            RequestDispatcher rd = request.getRequestDispatcher("/bookresponse");
            rd.forward(request, response);
        }catch (SQLException | ServletException e){
            e.printStackTrace();
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e){e.printStackTrace(); }
        try (Connection conn = DriverManager.getConnection(
                     Data.getSQL(), Data.getSQLUser(), Data.getSQLPass());
             Statement stmt = conn.createStatement()
        ) {
            JsonNode node = Data.getJSON(request);

            int id = node.get("id").asInt();//Integer.parseInt(request.getParameter("id"));
            boolean chkID = checkID(stmt, id);

            request.setAttribute("IDStatus", chkID);
            if (chkID) {
                String[] book = getBook(stmt, id);
                request.setAttribute("id", id);
                request.setAttribute("title", book[0]);
                request.setAttribute("author", book[1]);
                request.setAttribute("year", book[2]);

                String delete = "DELETE FROM books WHERE id = " + id;
                stmt.execute(delete);
                String saveId = "INSERT INTO bookid VALUES (" + id + ")";
                stmt.execute(saveId);

                RequestDispatcher rd = request.getRequestDispatcher("/bookresponse");
                rd.forward(request, response);
            }else {

            }

        }catch (SQLException | ServletException e){
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e){e.printStackTrace(); }
        try (Connection conn = DriverManager.getConnection(
                Data.getSQL(), Data.getSQLUser(), Data.getSQLPass());
             Statement stmt = conn.createStatement()){

            ArrayList<String> bookList = new ArrayList<>();
            String books = "SELECT * FROM books";
            ResultSet rs = stmt.executeQuery(books);

            while(rs.next()) {
                bookList.add(rs.getString("id"));
                bookList.add(rs.getString("title"));
                bookList.add(rs.getString("author"));
                bookList.add(rs.getString("year"));
                if(rs.getString("checkedOut").equals("0"))
                    bookList.add("in");
                else bookList.add("out");
            }
            request.setAttribute("books", bookList);
            RequestDispatcher rd = request.getRequestDispatcher("/bookresponse");
            rd.forward(request, response);

        }catch (SQLException | ServletException e) {
            e.printStackTrace();
        }
    }

    //Checks id table for available ids
    private boolean checkIDTable(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM bookid");
        rs.next();
        return !rs.getString("COUNT(*)").equals("0");
    }

    //Gets first available id from id table
    private int nextID(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT id FROM bookid");
        rs.next();
        return Integer.parseInt(rs.getString("id"));
    }

    //Gets next available id if id table is empty
    private int getID(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM books");
        rs.next();
        return Integer.parseInt(rs.getString("COUNT(*)")) + 1000;
    }

    //Checks if id is valid
    private boolean checkID(Statement stmt, int id) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM books WHERE id = " + id);
        rs.next();
        return rs.getString("COUNT(*)").equals("1");
    }

    //Gets book's title, author, and publishing year
    private String[] getBook(Statement stmt, int id) throws SQLException {
        ResultSet rs = stmt.executeQuery(
                "SELECT title, author, year FROM books WHERE id = " + id);
        rs.next();
        String[] book = new String[3];
        book[0] = rs.getString("title");
        book[1] = rs.getString("author");
        book[2] = rs.getString("year");
        return book;
    }
}
