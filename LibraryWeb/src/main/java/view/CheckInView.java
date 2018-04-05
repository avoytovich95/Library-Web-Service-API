package view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;

import javax.servlet.http.*;
import java.io.*;

/**
 * Created by Alex on 3/8/2018.
 */
public class CheckInView extends HttpServlet {

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
                String guest = request.getAttribute("guest").toString();
                String date = request.getAttribute("date").toString();
                int weeks = (Integer)request.getAttribute("weeks");
                int days = (Integer)request.getAttribute("days");
                double fee = (Double)request.getAttribute("fee");

                node.put("Status", "checked in");
                node.put("book", book);
                node.put("guest", guest);
                node.put("date", date);
                node.put("days", days);
                node.put("weeks", weeks);
                node.put("fee", fee);
            } else {
                if (!bookStatus)
                    node.put("bookError", "unknown book id");
                else if (!bookAvail)
                    node.put("bookError", "book already in");
                if (!guestStatus)
                    node.put("guestError", "unknown guest id");
            }
            array.add(node);
            out.write(array.toString());
        }
    }
}
