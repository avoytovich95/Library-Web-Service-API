package mappers;

import database.Book;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alex on 4/22/2018.
 */
public class BookMapper implements RowMapper<Book> {

    @Override
    public Book map(ResultSet rs, StatementContext sc) throws SQLException{
        return new Book(rs.getInt("id"), rs.getString("title"),
                rs.getString("author"), rs.getInt("year"),
                rs.getBoolean("status"), rs.getString("guest"),
                rs.getString("date"));
    }
}
