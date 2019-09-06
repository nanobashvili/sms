package ge.digitaledu.sms.model;

import ge.digitaledu.sms.common.Gender;
import ge.digitaledu.sms.common.LectureState;
import ge.digitaledu.sms.common.StudentStatus;
import ge.digitaledu.sms.model.entity.Lecture;
import ge.digitaledu.sms.model.entity.Student;
import ge.digitaledu.sms.utils.Utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentModelService extends ModelController implements Model {
    private Class<Student> tableName;

    public StudentModelService(Class<Student> tableName) {
        this.tableName = tableName;
        try {
            databaseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Object obj) {
        openConnection();

        Student student = (Student) obj;
        String insertStr = "" +
                "INSERT INTO `" + tableName.getSimpleName() + "` (id, firstname, lastname, birthdate, gender, lecture, status, pn) " +
                "VALUES ('" + student.getId() + "', '" + student.getFirstName() + "', '" + student.getLastName() + "', '" + student.getBirthDate() + "', '" + student.getGender().name() + "', '" + student.getLecture().getId() + "', '" + student.getStatus().name() + "', '" + student.getPn() + "')";

        try {
            statement.executeUpdate(insertStr);
            System.out.println("Successfully added to database");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void delete(int id) {
        String deleteStr = "DELETE FROM `" + tableName.getSimpleName() + "` WHERE id = " + id;

        openConnection();
        try {
            statement.executeUpdate(deleteStr);
            System.out.println("Successfully deleted from database");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void update(Object obj, int id) {
        Student student = (Student) obj;

        StringBuilder updateUtils = new StringBuilder("UPDATE `" + tableName.getSimpleName() + "` SET ");
        updateUtils.append("firstName = '" + student.getFirstName() + "',");
        updateUtils.append("lastName = '" + student.getLastName() + "',");
        updateUtils.append("birthDate = '" + student.getBirthDate() + "',");
        updateUtils.append("gender = '" + student.getGender() + "',");
        updateUtils.append("lecture = " + student.getLecture().getId() + ",");
        updateUtils.append("status = '" + student.getStatus() + "',");
        updateUtils.append("pn = '" + student.getPn() + "'");
        updateUtils.append(" WHERE id = " + id);

        openConnection();
        try {
            statement.executeUpdate(updateUtils.toString());
            System.out.println("Successfully updated table");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public List getAll() {
        String getStr = "SELECT l.id as lectureId, s.*, l.* FROM `" + tableName.getSimpleName() + "` as s LEFT JOIN `lecture` as l ON s.lecture = l.id";
        List<Student> students = new ArrayList<>();

        openConnection();
        try {
            ResultSet rs = statement.executeQuery(getStr);
            while (rs.next()) {
                Student student = new Student();
                Lecture lecture = new Lecture();

                student.setId(rs.getInt("id"));
                student.setFirstName(rs.getString("firstname"));
                student.setLastName(rs.getString("lastname"));
                student.setStatus(StudentStatus.valueOf(rs.getString("status")));
                student.setPn(rs.getString("pn"));
                student.setGender(Gender.valueOf(rs.getString("gender")));
                student.setBirthDate(rs.getString("birthdate"));

                lecture.setId(rs.getInt("lectureId"));
                lecture.setState(LectureState.valueOf(rs.getString("state")));
                lecture.setLectureName(rs.getString("lecturename"));
                student.setLecture(lecture);

                // ლისტში დამატება ერთეული ელემენტის
                students.add(student);
            }

            if (rs != null) rs.close();

            System.out.println("Successfully selected from database");
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return null;
    }

    @Override
    public Object getSingle(int id) {
        String getStr = "SELECT l.id as lectureId, s.*, l.* FROM `" + tableName.getSimpleName() + "` as s LEFT JOIN `lecture` as l ON s.lecture = l.id WHERE s.id = " + id;
        Student student = new Student();
        Lecture lecture = new Lecture();

        openConnection();
        try {
            ResultSet rs = statement.executeQuery(getStr);
            if (rs.next()) {
                student.setId(rs.getInt("id"));
                student.setFirstName(rs.getString("firstname"));
                student.setLastName(rs.getString("lastname"));
                student.setStatus(StudentStatus.valueOf(rs.getString("status")));
                student.setPn(rs.getString("pn"));
                student.setGender(Gender.valueOf(rs.getString("gender")));
                student.setBirthDate(rs.getString("birthdate"));

                lecture.setId(rs.getInt("lectureId"));
                lecture.setState(LectureState.valueOf(rs.getString("state")));
                lecture.setLectureName(rs.getString("lecturename"));
                student.setLecture(lecture);
            }

            if (rs != null) rs.close();

            return student;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return null;
    }
}
