import controllers.*;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.*;
import mappers.*;
import org.jdbi.v3.core.Jdbi;

/**
 * Created by Alex on 4/16/2018.
 */
public class Library extends Application<LibraryConfig> {

    public static void main(String[] args) throws Exception {
        new Library().run(args);
    }

    @Override
    public String getName() {
        return "Library-web";
    }

    @Override
    public void initialize(Bootstrap<LibraryConfig> bootstrap) {

    }

    @Override
    public void run(LibraryConfig config, Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, config.getDb(), "mysql");

        jdbi.registerRowMapper(new BookMapper());
        jdbi.registerRowMapper(new GuestMapper());

        BookController book = new BookController(jdbi);
        environment.jersey().register(book);
        GuestController guest = new GuestController(jdbi);
        environment.jersey().register(guest);
        TaskController task = new TaskController(jdbi);
        environment.jersey().register(task);
    }
}
