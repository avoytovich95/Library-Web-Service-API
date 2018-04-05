package view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;

import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Alex on 3/1/2018.
 */
public class BookView extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        ObjectMapper map = new ObjectMapper();
        ArrayNode array = map.createArrayNode();

        try (PrintWriter out = response.getWriter()) {
            int id = (Integer)request.getAttribute("id");
            String title = request.getAttribute("title").toString();
            String author = request.getAttribute("author").toString();
            int year = (Integer)request.getAttribute("year");

            ObjectNode node = map.createObjectNode();
            node.put("id", id);
            node.put("title", title);
            node.put("author", author);
            node.put("year", year);
            array.add(node);
            out.write(array.toString());
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        ObjectMapper map = new ObjectMapper();
        ArrayNode array = map.createArrayNode();

        try(PrintWriter out = response.getWriter()) {
            boolean idStatus = (Boolean)request.getAttribute("IDStatus");
            ObjectNode node = map.createObjectNode();
            if(idStatus) {
                int id = (Integer)request.getAttribute("id");
                String title = request.getAttribute("title").toString();
                String author = request.getAttribute("author").toString();
                int year = (Integer)request.getAttribute("year");

                node.put("Status", "deleted");
                node.put("id", id);
                node.put("title", title);
                node.put("author", author);
                node.put("year", year);
            }else
                node.put("idError", "Unknown Book ID");
            array.add(node);
            out.write(array.toString());
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        ObjectMapper map = new ObjectMapper();
        ArrayNode array = map.createArrayNode();

        try(PrintWriter out = response.getWriter()) {
            ArrayList<String> books = (ArrayList<String>)request.getAttribute("books");
            for (int i = 0; i < books.size(); i += 5) {
                ObjectNode node = map.createObjectNode();
                node.put("id", books.get(i));
                node.put("title", books.get(i+1));
                node.put("author", books.get(i+2));
                node.put("year", books.get(i+3));
                node.put("status", books.get(i+4));
                array.add(node);
            }
            out.write(array.toString());
        }
    }
}
