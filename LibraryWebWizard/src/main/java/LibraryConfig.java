import com.fasterxml.jackson.annotation.JsonProperty;
import com.mysql.cj.jdbc.MysqlDataSourceFactory;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Alex on 4/16/2018.
 */
public class LibraryConfig extends Configuration{

    @Valid
    @NotNull
    private DataSourceFactory db = new DataSourceFactory();


    @JsonProperty("Database")
    public void setDb(DataSourceFactory db) {
        this.db = db;
    }

    @JsonProperty("Database")
    public DataSourceFactory getDb() {
        return this.db;
    }
}
