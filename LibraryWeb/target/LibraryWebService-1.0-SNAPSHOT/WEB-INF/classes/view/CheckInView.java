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
            boolean bookStatus = Boolean.parseBoolean(request.getAttribute("bookStatus").toString());
            boolean guestStatus = Boolean.parseBoolean(request.getAttribute("guestStatus").toString());
            boolean bookAvail = Boolean.parseBoolean(request.getAttribute("bookAvail").toString());

            ObjectNode node = map.createObjectNode();
            if (bookStatus && guestStatus && bookAvail) {
                String book = request.getAttribute("book").toString();
                String guest = request.getAttribute("guest").toString();
                String date = request.getAttribute("date").toString();
                int weeks = Integer.parseInt(request.getAttribute("weeks").toString());
                int days = Integer.parseInt(request.getAttribute("days").toString());
                double fee = Double.parseDouble(request.getAttribute("fee").toString());

                node.put("Status", "Checked In");
                node.put("book", book);
                node.put("guest", guest);
                node.put("date", date);
                node.put("days", days);
                node.put("weeks", weeks);
                node.put("$fee", fee);
            } else {
                if (!bookStatus)
                    node.put("bookError", "Unkown Book ID");
                if (!bookAvail)
                    node.put("bookError", "Book already checked in");
                if (!guestStatus)
                    node.put("guestError", "Unknown Guest ID");
            }
            array.add(node);
            out.write(array.toString());
        }
    }
}
