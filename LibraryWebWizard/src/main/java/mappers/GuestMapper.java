package mappers;

import database.Guest;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.*;

/**
 * Created by Alex on 4/22/2018.
 */
public class GuestMapper implements RowMapper<Guest> {

    @Override
    public Guest map(ResultSet rs, StatementContext sc) throws SQLException{
        return new Guest(rs.getString("id"),
                rs.getString("first"),
                rs.getString("last"),
                rs.getDouble("fee"));
    }
}
