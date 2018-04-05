package view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;

import javax.servlet.http.*;
import java.io.*;

/**
 * Created by Alex on 3/1/2018.
 */
public class LateFeeView extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        ObjectMapper map = new ObjectMapper();
        ArrayNode array = map.createArrayNode();

        try (PrintWriter out = response.getWriter()){
            int days = (Integer)request.getAttribute("days");
            int weeks = (Integer)request.getAttribute("weeks");
            double fee = (Double)request.getAttribute("fee");

            ObjectNode obj = map.createObjectNode();
            obj.put("days", days);
            obj.put("weeks", weeks);
            obj.put("fee", fee);
            array.add(obj);

            out.write(array.toString());
        }
    }
}
