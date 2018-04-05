package view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;

import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Alex on 3/6/2018.
 */
public class GuestView extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        ObjectMapper map = new ObjectMapper();
        ArrayNode array = map.createArrayNode();

        try(PrintWriter out = response.getWriter()) {
            String id = request.getAttribute("id").toString();
            String first = request.getAttribute("first").toString();
            String last = request.getAttribute("last").toString();

            ObjectNode guestNode = map.createObjectNode();
            guestNode.put("id", id);
            guestNode.put("first", first);
            guestNode.put("last", last);

            array.add(guestNode);
            out.write(array.toString());
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        ObjectMapper map = new ObjectMapper();
        ArrayNode array = map.createArrayNode();

        try(PrintWriter out = response.getWriter()) {
            boolean check = Boolean.parseBoolean(request.getAttribute("check").toString());
            if (check) {
                String id = request.getAttribute("id").toString();
                String first = request.getAttribute("first").toString();
                String last = request.getAttribute("last").toString();
                ObjectNode guestNode = map.createObjectNode();
                guestNode.put("status", "deleted");
                guestNode.put("id", id);
                guestNode.put("first", first);
                guestNode.put("last", last);

                array.add(guestNode);
                out.write(array.toString());
            }else {
                ObjectNode error = map.createObjectNode();
                error.put("error", "Bad guest id");
                array.add(error);
                out.write(array.toString());
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        ObjectMapper map = new ObjectMapper();
        ArrayNode array = map.createArrayNode();

        try(PrintWriter out = response.getWriter()) {
            ArrayList<String> guestList = (ArrayList<String>)request.getAttribute("guests");
            for (int i = 0; i < guestList.size(); i += 3) {
                ObjectNode node = map.createObjectNode();
                node.put("id", guestList.get(i));
                node.put("first", guestList.get(i+1));
                node.put("last", guestList.get(i+2));
                array.add(node);
            }
            out.write(array.toString());
        }
    }
}
