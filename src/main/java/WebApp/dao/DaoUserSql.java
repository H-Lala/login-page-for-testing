package WebApp.dao;

import WebApp.db.DbConnection;
import WebApp.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DaoUserSql implements Dao<User> {
    private Connection connection;
    private List<User> users;

    public DaoUserSql() {
        users = new LinkedList<>();
        this.connection = DbConnection.getConnection();
    }
    public DaoUserSql(Connection connection){
        this.connection=connection;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void save(User item) {
        String SQLI = "INSERT INTO users(name,surname,username,email,password) VALUES(?,?,?,?,?)";
        User user = null;

        try {
            PreparedStatement statement = connection.prepareStatement(SQLI);
            statement.setString(1, item.getName());
            statement.setString(2, item.getSurname());
            statement.setString(3, item.getUsername());
            statement.setString(4, item.getEmail());
            statement.setString(5, item.getPassword());
            statement.execute();
            user = new User(item.getName(), item.getSurname(), item.getUsername(), item.getEmail()
                    , item.getPassword());
            users.add(user);
            System.out.println("save postgres");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public User getByLogin(User item) {
        User user = null;
        String SQLS = "SELECT * FROM users WHERE username = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(SQLS);
            stm.setString(1, item.getUsername());
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                user = new User( id,name, surname,username, email,password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(User item) {

    }
}
