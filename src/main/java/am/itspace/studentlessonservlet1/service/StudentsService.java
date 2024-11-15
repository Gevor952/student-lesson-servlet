package am.itspace.studentlessonservlet1.service;

import am.itspace.studentlessonservlet1.db.DBConnectionProvider;
import am.itspace.studentlessonservlet1.model.Lessons;
import am.itspace.studentlessonservlet1.model.Students;
import am.itspace.studentlessonservlet1.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsService {

    public static final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Students students) {
        String sql = "insert into students(name, surname, age, lessons_id) values(?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, students.getName());
            ps.setString(2, students.getSurname());
            ps.setInt(3, students.getAge());
            ps.setInt(4, students.getLesson().getId());
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
                + "INNER JOIN lessons ON students.lessons_id = lessons.id";
        List<Students> students = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Students student = Students.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .surname(rs.getString(3))
                        .age(rs.getInt(4))
                        .lesson(Lessons.builder()
                                .id(rs.getInt(6))
                                .duration(DateUtil.timeForDate(rs.getString(7)))
                                .lecturerName(rs.getString(8))
                                .price(rs.getDouble(9))
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
                        .age(rs.getInt(4))
                        .lesson(Lessons.builder()
                                .id(rs.getInt(6))
                                .duration(DateUtil.timeForDate(rs.getString(7)))
                                .lecturerName(rs.getString(8))
                                .price(rs.getDouble(9))
                                .build())
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public void update(Students students) {
        String sql = "UPDATE students SET name= ?,surname= ?,age= ?,lessons_id= ? WHERE id = " + students.getId();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, students.getName());
            preparedStatement.setString(2, students.getSurname());
            preparedStatement.setInt(3, students.getAge());
            preparedStatement.setInt(4, students.getLesson().getId());
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
            throw new RuntimeException(e);
        }
    }
}
