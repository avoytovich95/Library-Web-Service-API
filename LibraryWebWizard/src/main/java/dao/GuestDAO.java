package dao;

import database.Guest;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

/**
 * Created by Alex on 4/22/2018.
 */
public interface GuestDAO {

    @SqlQuery("SELECT * FROM guest")
    public List<Guest> get();

    @SqlQuery("SELECT * FROM guest WHERE id = :id")
    public Guest get(@Bind("id") String id);

    @SqlQuery("INSERT INTO guest VALUES (:id, :first, :last, 0.0)")
    public void insert(@Bind("id") String id, @Bind("first") String first, @Bind("last") String last);

    @SqlQuery("DELETE FROM guest WHERE id = :id")
    public void delete(@Bind("id") String id);
}
