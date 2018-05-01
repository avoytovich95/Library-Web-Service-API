package controllers;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dao.*;
import database.*;
import io.dropwizard.jersey.PATCH;
import org.jdbi.v3.core.Jdbi;
import skeletons.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alex on 4/23/2018.
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class TaskController {

    private final BookDAO bookDAO;
    private final GuestDAO guestDAO;

    public TaskController(Jdbi db) {
        bookDAO = db.onDemand(BookDAO.class);
        guestDAO = db.onDemand(GuestDAO.class);
    }

    @Path("latefee")
    @GET
    @Timed
    public Response lateFee(@QueryParam("date") String date) throws ParseException{
        System.out.println(date);
        long days = getDays(date);
        DateObject dateObject = new DateObject(days);
        return Response.ok(dateObject).build();
    }

    @Path("checkout")
    @PATCH
    public Response checkOut(ObjectNode body){
        Book book = bookDAO.get(body.get("book").asInt());
        Guest guest = guestDAO.get(body.get("guest").asText());
        if (book.getStatus()) {
            book.checkOut(guest.getId(), getDate());
            bookDAO.checkOut(guest.getId(), book.getDate(), book.getId());
            GuestBook pair = new GuestBook(guest, book);
            return Response.ok(pair).build();
        } else
            return Response.notModified("Book unavailable").build();
    }

    @Path("checkin")
    @PATCH
    public Response checkIn(ObjectNode body) throws ParseException{
        Book book = bookDAO.get(body.get("book").asInt());
        Guest guest = guestDAO.get(body.get("guest").asText());
        if (!book.getStatus()) {
            DateObject dateObject = new DateObject(getDays(book.getDate()));
            book.checkIn();
            guest.addFee(dateObject.getFee());
            bookDAO.checkIn(book.getId());
            guestDAO.update(guest.getFee(), guest.getId());
            GuestBookDate set = new GuestBookDate(guest, book, dateObject);
            return Response.ok(set).build();
        } else
            return Response.notModified("Book already in").build();
    }

    private long getDays(String date)throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
        Date checkout = dateFormat.parse(date);
        Date current = new Date();
        long diff = current.getTime() - checkout.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    private String getDate() {
        Calendar cal = Calendar.getInstance();
        int mnth = cal.get(Calendar.MONTH) + 1;
        String month;
        if (mnth < 10)
            month = "0" + mnth;
        else month = mnth + "";

        int dt = cal.get(Calendar.DATE);
        String date;
        if (dt < 10)
            date = "0" + dt;
        else date = dt + "";
        return  month + date + cal.get(Calendar.YEAR);
    }
}
