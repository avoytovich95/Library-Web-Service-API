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
            int days = Integer.parseInt(request.getAttribute("days").toString());
            int weeks = Integer.parseInt(request.getAttribute("weeks").toString());
            double fee = Double.parseDouble(request.getAttribute("fee").toString());

            ObjectNode obj = map.createObjectNode();
            obj.put("days", days);
            obj.put("weeks", weeks);
            obj.put("fee", fee);
            array.add(obj);

            out.write(array.toString());
        }
    }
}
