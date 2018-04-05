package control;

import com.fasterxml.jackson.databind.JsonNode;
import utility.Data;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.text.ParseException;

/**
 * Created by Alex on 2/22/2018.
 */
public class LateFeeServlet extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try (PrintWriter out = response.getWriter()) {
//            String checkOut = request.getParameter("date");

            JsonNode node = Data.getJSON(request);
            String checkOut = node.get("date").textValue();

            long dayCount = Data.getDays(checkOut);
            int weeks = (int) dayCount / 7;
            int days = (int) dayCount % 7;

            request.setAttribute("days", days);
            request.setAttribute("weeks", weeks);
            request.setAttribute("fee", (weeks * 0.25));

            RequestDispatcher rd = request.getRequestDispatcher("/latefeeresponse");
            rd.forward(request, response);
        } catch (ParseException | ServletException e) {
            e.printStackTrace();
        }
    }
}
