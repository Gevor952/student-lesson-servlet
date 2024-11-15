package am.itspace.studentlessonservlet1.service;

import am.itspace.studentlessonservlet1.db.DBConnectionProvider;
import am.itspace.studentlessonservlet1.model.Lessons;
import am.itspace.studentlessonservlet1.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonsService {
    Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Lessons lesson) {
        String sql = "INSERT INTO lessons (duration , lecturerName, price) VALUES (?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setTime(1, new Time(lesson.getDuration().getTime()));
            ps.setString(2, lesson.getLecturerName());
            ps.setDouble(3, lesson.getPrice());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                lesson.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Lessons> getAllLessons() {
        String sql = "SELECT * FROM lessons";
        List<Lessons> lessons = new ArrayList<>();
        try (Statement s = connection.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Lessons lesson = Lessons.builder()
                        .id(rs.getInt("id"))
                        .lecturerName(rs.getString("lecturerName"))
                        .duration(DateUtil.timeForDate(String.valueOf(rs.getTime("duration"))))
                        .price(rs.getDouble("price"))
                        .build();
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }


    public void delete(int id) {
        String sql = "DELETE FROM lessons WHERE id = " + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Lessons lesson) {
        String sql = "UPDATE lessons SET  lecturerName = ?, duration = ?, price = ? WHERE id = " + lesson.getId();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, lesson.getLecturerName());
            preparedStatement.setTime(2, new Time(lesson.getDuration().getTime()));
            preparedStatement.setDouble(3, lesson.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Lessons getLessonById(int id) {
        String sql = "SELECT * FROM lessons WHERE id = " + id;
        Lessons lessons = null;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                lessons = Lessons.builder()
                        .id(resultSet.getInt("id"))
                        .lecturerName(resultSet.getString("lecturerName"))
                        .duration(DateUtil.timeForDate(String.valueOf(resultSet.getTime("duration"))))
                        .price(resultSet.getDouble("price"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lessons;
    }
}


