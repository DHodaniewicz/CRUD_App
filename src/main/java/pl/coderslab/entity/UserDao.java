package main.java.pl.coderslab.entity;

import main.java.pl.coderslab.OOP_SQL_WORKSHOP.DBUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_USER_QUERY = "INSERT INTO users (username, email, password) VALUES (?,?,?)";
    private static final String GET_USER_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, username = ?, password = ? WHERE id = ?";
    private static final String SELECT_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM users";

    public UserDao() {

    }


    public User create(User user) {
        try (Connection conn = DBUtil.connect("workshop2")) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            System.out.println("Dodano uzytkowika: " + user.getUserName());
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read (int userId) {
        try (Connection conn = DBUtil.connect("workshop2")) {
            PreparedStatement statement = conn.prepareStatement(GET_USER_QUERY);
            statement.setString(1, String.valueOf(userId));
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                User user = new User();
                user.setEmail( resultSet.getString("email"));
                user.setUserName(resultSet.getString("username"));
                user.setId(Integer.parseInt(resultSet.getString("id")));
                System.out.println("Wczytano dane uzytkownika o id: " + user.getId());
                return user;
            }
            else {
                System.out.println("Brak uzytkownika o takim id w bazie danych");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(User user) {
        try (Connection conn = DBUtil.connect("workshop2")) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setString(4, String.valueOf(user.getId()));
            statement.executeUpdate();
            System.out.println("Uaktualniono dane uzytkownika o id: " + user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser (int id) {
        try (Connection conn = DBUtil.connect("workshop2")) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_BY_ID_QUERY);
            statement.setString(1, String.valueOf(id));
            statement.executeUpdate();
            if (checkDatabaseForUser(id)) {
                System.out.println("Usunieto uzytkownika o id:" + id);
            }
            else {
                System.out.println("Uzytkownika o podanym id nie bylo w bazie danych");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User[] findAll() {
        User[] users = new User[0];
        try (Connection conn = DBUtil.connect("workshop2")) {
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                users = addToArray(user, users);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1); // Tworzymy kopię tablicy powiększoną o 1.
        tmpUsers[tmpUsers.length -1] = u; // Dodajemy obiekt na ostatniej pozycji.
        return tmpUsers; // Zwracamy nową tablicę.
    }


    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


    public boolean checkDatabaseForUser (int id) {
        try (Connection conn = DBUtil.connect("workshop2")) {
            PreparedStatement statement = conn.prepareStatement(SELECT_USER_BY_ID_QUERY);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void printAllUsers (User [] users) {
        for (int i = 0; i < users.length; i++) {
            System.out.println("User id: " + users[i].getId());
            System.out.println("Username: " + users[i].getUserName());
            System.out.println("User email: " + users[i].getEmail());
            System.out.println();
        }
    }


}
