package skeletons;

import com.fasterxml.jackson.annotation.JsonProperty;
import database.Book;
import database.Guest;

/**
 * Created by Alex on 4/23/2018.
 */
public class GuestBook {

    @JsonProperty
    private Guest guest;

    @JsonProperty
    private Book book;

    public GuestBook(Guest guest, Book book) {
        this.guest = guest; this.book = book;
    }
}

