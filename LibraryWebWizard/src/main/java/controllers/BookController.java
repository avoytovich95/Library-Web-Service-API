package controllers;

import com.codahale.metrics.annotation.Timed;
import dao.BookDAO;
import database.Book;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * Created by Alex on 4/20/2018.
 */

@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
public class BookController {

    private final BookDAO bookDAO;

    public BookController(Jdbi db){
        bookDAO = db.onDemand(BookDAO.class);
    }

    @GET
    @Timed
    public Response getBooks(@QueryParam("id") Optional<Integer> id) {
        if (id.isPresent()) {
            return Response.ok(bookDAO.get(id.get())).build();
        } else {
            return Response.ok(bookDAO.get()).build();
        }
    }

    @POST
    @Timed
    public Response addBook(Book book) throws URISyntaxException{
        bookDAO.insert(book.getTitle(), book.getAuthor(), book.getYear());
        book.setId(bookDAO.getId());
//        return Response.accepted(book).build();
//        URI uri = new URI("/book?id=" + book.getId());
//        return Response.created(uri).build();
        return Response.created(URI.create("/library/book?id="+book.getId())).entity(book).build();
    }

    @DELETE
    @Timed
    public Response deleteBook(@QueryParam("id") int id) {
        Book book = bookDAO.get(id);
        book.setStatus(false);
        bookDAO.delete(id);
        return Response.ok(book).build();
//        return Response.status(204).build();
    }
}
