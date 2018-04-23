package dao;

import database.Book;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

/**
 * Created by Alex on 4/20/2018.
 */
public interface BookDAO{

    @SqlQuery("SELECT * FROM book")
    public List<Book> get();

    @SqlQuery("SELECT LAST_INSERT_ID()")
    public int getId();

    @SqlQuery("SELECT * FROM book WHERE id = :id")
    public Book get(@Bind("id") int id);

    @SqlUpdate("INSERT INTO book VALUES (DEFAULT, :title, :author, :year, true, null, null)")
    public void insert(@Bind("title") String title, @Bind("author") String author, @Bind("year") int year);

    @SqlUpdate("DELETE FROM book Where id = :id")
    public void delete(@Bind("id") int id);

    @SqlUpdate("UPDATE book SET " +
            "status = false," +
            "guest = :guest," +
            "date = :date " +
            "WHERE id = :id")
    public void checkOut(@Bind("guest") String guest, @Bind("date") String date, @Bind("id") int id);

    @SqlUpdate("UPDATE book SET " +
            "status = true," +
            "guest = null," +
            "date = null " +
            "WHERE id = :id")
    public void checkIn(@Bind("id") int id);
}
