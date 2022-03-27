package jpa.service;

import jpa.entitymodels.Course;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

public class TestCourse {
    //    public Course getCourseById(int cId)
//    public List<Course> getAllCourses()
    static SessionFactory factory = new Configuration().configure().buildSessionFactory();  //ugh ugh ugh
    static CourseService cs = smsGetCourseService.getCourseService();

    @Test
    public void testGetAllCourses() {
        String[][] testList = {
                {"1", "English", "Anderea Scamaden"},
                {"2", "Mathematics", "Eustace Niemetz"},
                {"3", "Anatomy", "Reynolds Pastor"},
                {"4", "Organic Chemistry", "Odessa Belcher"},
                {"5", "Physics", "Dani Swallow"},
                {"6", "Digital Logic", "Glenden Reilingen"},
                {"7", "Object Oriented Programming", "Giselle Ardy"},
                {"8", "Data Structures", "Carolan Stoller"},
                {"9", "Politics", "Carmita De Maine"},
                {"10", "Art", "Kingsly Doxsey"}};
        List<Course> courses = cs.getAllCourses();
        boolean result = true;
        if (courses.size() == 10) {
            Course course;
            for (int i = 0; i < 10; i++) {
                course = courses.get(i);
                if ( (course.getCId() != Integer.parseInt(testList[i][0]))
                    ||!(course.getCName().equals(testList[i][1]))
                        || !(course.getCInstructorName().equals(testList[i][2])) ) {
                    result = false;
                    break;
                }
            }
        }
        assert (result);
    }

    @ParameterizedTest
    @CsvSource({"8, Data Structures"})
    public void testGetCoursesbyId(int cId,String expected) {
        Course course =cs.getCourseById(cId);
        String cActual = course.getCName();
        assert(expected.equals(cActual));
         return;
    }
}
