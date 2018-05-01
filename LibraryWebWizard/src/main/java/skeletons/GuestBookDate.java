package skeletons;

import com.fasterxml.jackson.annotation.JsonProperty;
import database.Book;
import database.Guest;

/**
 * Created by Alex on 4/23/2018.
 */
public class GuestBookDate extends GuestBook {

    @JsonProperty
    DateObject date;

    public GuestBookDate(Guest guest, Book book, DateObject date) {
        this.guest = guest; this.book = book; this.date = date;
    }
}
