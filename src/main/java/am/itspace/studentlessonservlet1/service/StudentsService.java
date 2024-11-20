package am.itspace.studentlessonservlet1.service;

import am.itspace.studentlessonservlet1.db.DBConnectionProvider;
import am.itspace.studentlessonservlet1.model.Lessons;
import am.itspace.studentlessonservlet1.model.Students;
import am.itspace.studentlessonservlet1.model.User;
import am.itspace.studentlessonservlet1.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsService {

    public static final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Students students) {
        String sql = "insert into students(name, surname, email, age, lessons_id, user_id) values(?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, students.getName());
            ps.setString(2, students.getSurname());
            ps.setString(3, students.getEmail());
            ps.setInt(4, students.getAge());
            ps.setInt(5, students.getLesson().getId());
            ps.setInt(6, students.getUser().getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                students.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Students> getAll() {
        String sql = "select * from students "
                + "INNER JOIN lessons ON students.lessons_id = lessons.id " + "INNER JOIN user ON students.user_id = user.id";
        List<Students> students = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Students student = Students.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .surname(rs.getString(3))
                        .email(rs.getString(4))
                        .age(rs.getInt(5))
                        .lesson(Lessons.builder()
                                .id(rs.getInt(8))
                                .duration(DateUtil.timeForDate(rs.getString(9)))
                                .lecturerName(rs.getString(10))
                                .price(rs.getDouble(11))
                                .build())
                        .user(User.builder()
                                .id(rs.getInt(12))
                                .name(rs.getString(13))
                                .surname(rs.getString(14))
                                .email(rs.getString(15))
                                .password(rs.getString(16))
                                .build())
                        .build();
                students.add(student);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }



    public boolean getAllByEmail(String email, int userId) {
        String sql = "SELECT * FROM students WHERE email = ? AND user_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, userId);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }



    public List<Students> getAllByUserId(int userId) {
        String sql = "select * from students "
                + "INNER JOIN lessons ON students.lessons_id = lessons.id " + "INNER JOIN user ON students.user_id = user.id " + "WHERE students.user_id = " + userId;
        List<Students> students = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Students student = Students.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .surname(rs.getString(3))
                        .email(rs.getString(4))
                        .age(rs.getInt(5))
                        .lesson(Lessons.builder()
                                .id(rs.getInt(8))
                                .duration(DateUtil.timeForDate(rs.getString(9)))
                                .lecturerName(rs.getString(10))
                                .price(rs.getDouble(11))
                                .build())
                        .user(User.builder()
                                .id(rs.getInt(12))
                                .name(rs.getString(13))
                                .surname(rs.getString(14))
                                .email(rs.getString(15))
                                .password(rs.getString(16))
                                .build())
                        .build();
                students.add(student);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Students getById(int id) {
/*
        String sql = "select * from students INNER JOIN lessons ON students.lessons_id = lessons.id where id = " + id;
*/
        String sql = "SELECT * FROM students " + "INNER JOIN lessons ON students.lessons_id = lessons.id" + " WHERE students.id = " +id;
        Students student = null;
        try(Statement statement = connection.createStatement()) {
            statement.executeQuery(sql);
            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                student = Students.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .surname(rs.getString(3))
                        .email(rs.getString(4))
                        .age(rs.getInt(5))
                        .lesson(Lessons.builder()
                                .id(rs.getInt(8))
                                .duration(DateUtil.timeForDate(rs.getString(9)))
                                .lecturerName(rs.getString(10))
                                .price(rs.getDouble(11))
                                .build())
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public void update(Students students) {
        String sql = "UPDATE students SET name= ?,surname= ?, email = ? ,age= ?,lessons_id= ? WHERE id = " + students.getId();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, students.getName());
            preparedStatement.setString(2, students.getSurname());
            preparedStatement.setString(3, students.getEmail());
            preparedStatement.setInt(4, students.getAge());
            preparedStatement.setInt(5, students.getLesson().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id = " + id;
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
