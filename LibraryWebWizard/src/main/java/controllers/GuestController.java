package controllers;

import com.codahale.metrics.annotation.Timed;
import dao.GuestDAO;
import database.Guest;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * Created by Alex on 4/22/2018.
 */
@Path("/guest")
@Produces(MediaType.APPLICATION_JSON)
public class GuestController {

    private final GuestDAO guestDAO;

    public GuestController(Jdbi db) {
        guestDAO = db.onDemand(GuestDAO.class);
    }

    @GET
    @Timed
    public Response getGuests(@QueryParam("id") Optional<String> id) {
        if (id.isPresent())
            return Response.ok(guestDAO.get(id.get())).build();
        else
            return Response.ok(guestDAO.get()).build();
    }

    @POST
    @Timed
    public Response addGuest(Guest guest) {
        guest.setId(randId(8));
        guestDAO.insert(guest.getId(), guest.getFirst(), guest.getLast());
        return Response.accepted(guest).build();
    }

    @DELETE
    @Timed
    public Response deleteBook(@QueryParam("id") String id) {
        Guest guest = guestDAO.get(id);
        guestDAO.delete(id);
        return Response.ok(guest).build();
//        return Response.status(204).build();
    }


    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = upper.toLowerCase();
    private static final String digits = "0123456789";
    private static final String alphanum = upper + lower + digits;

    private static String randId(int size) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; ++i)
            str.append(alphanum.charAt((int) (Math.random() * alphanum.length())));
        return str.toString();
    }
}
