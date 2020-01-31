package WebApp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    private static final String userName = "postgres";
    private static final String password = "postgres";

    public static Connection connection;

    private static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(dbUrl, userName, password);
    }


    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = connect();
            } catch (SQLException | ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }

        return connection;
    }

}
