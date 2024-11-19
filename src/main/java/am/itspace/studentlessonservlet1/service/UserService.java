package am.itspace.studentlessonservlet1.service;

import am.itspace.studentlessonservlet1.db.DBConnectionProvider;
import am.itspace.studentlessonservlet1.model.User;
import am.itspace.studentlessonservlet1.model.UserType;

import java.sql.*;

public class UserService {

    private static final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(User user) {
        String sql = "insert into user (name, surname, email, password, userType) values (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getUserType().name());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User grtByEmailAndPassword(String email, String password) {
        String sql = "select * from user where email = ? and password = ?";
        User user = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .userType(UserType.valueOf(resultSet.getString("userType")))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User grtByEmail(String email) {
        String sql = "select * from user where email = ?";
        User user = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = User.builder()
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .userType(UserType.valueOf(resultSet.getString("userType")))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
