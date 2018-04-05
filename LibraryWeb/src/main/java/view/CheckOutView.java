package view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;

import javax.servlet.http.*;
import java.io.*;

/**
 * Created by Alex on 3/2/2018.
 */
public class CheckOutView extends HttpServlet {

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        ObjectMapper map = new ObjectMapper();
        ArrayNode array = map.createArrayNode();

        try(PrintWriter out = response.getWriter()) {
            boolean bookStatus = (Boolean)request.getAttribute("bookStatus");
            boolean guestStatus = (Boolean)request.getAttribute("guestStatus");
            boolean bookAvail = (Boolean)request.getAttribute("bookAvail");

            ObjectNode node = map.createObjectNode();
            if (bookStatus && guestStatus && bookAvail) {
                String book = request.getAttribute("book").toString();
                String outTo = request.getAttribute("outTo").toString();
                String date = request.getAttribute("outDate").toString();
                node.put("status", "checked out");
                node.put("book", book);
                node.put("guest", outTo);
                node.put("date", date);
            }else if (!bookAvail && bookStatus && guestStatus){
                String book = request.getAttribute("book").toString();
                node.put("status", "Unavailable");
                node.put("book", book);
            }else {
                if (!bookStatus)
                    node.put("bookError", "Unkown Book ID");
                if (!guestStatus)
                    node.put("guestError", "Unkown Guest ID");
            }
            array.add(node);
            out.write(array.toString());
        }
    }
}
