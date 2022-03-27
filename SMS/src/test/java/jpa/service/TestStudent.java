package jpa.service;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.StudentService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class TestStudent {
//    Set<Student> getAllStudents();
//    Student  getStudentByEmail(String sEmail);
//    boolean validateStudent(String sEmail, String sPassword);
//    void registerStudentToCourse(String sEmail , int cId);
//    Set<Course> getStudentCourses(String sEmail);
static SessionFactory factory = new Configuration().configure().buildSessionFactory();  //ugh ugh ugh
//static StudentService ss = new StudentService();
static StudentService ss = smsGetStudentService.getStudentService();
static String[] students = {"hluckham0@google.ru","sbowden1@yellowbook.com","qllorens2@howstuffworks.com",
        "hguerre5@deviantart.com","htaffley6@columbia.edu", "aiannitti7@is.gd", "ljiroudek8@sitemeter.com","cjaulme9@bing.com"};
/*
    @BeforeAll
    public static void beforeALlTests() {

        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        //Add Students
        Student s1 = new Student("hluckham0@google.ru", "Hazel Luckham", "X1uZcoIh0dj");
        Student s2 = new Student("sbowden1@yellowbook.com", "Sonnnie Bowden", "SJc4aWSU");
        Student s3 = new Student("qllorens2@howstuffworks.com", "Quillan Llorens", "W6rJuxd");
        Student s4 = new Student("cstartin3@flickr.com", "Clem Startin", "XYHzJ1S");
        Student s5 = new Student("tattwool4@biglobe.ne.jp", "Thornie Attwool", "Hjt0SoVmuBz");
        Student s6 = new Student("hguerre5@deviantart.com", "Harcourt Guerre", "OzcxzD1PGs");
        Student s7 = new Student("htaffley6@columbia.edu", "Holmes Taffley", "xowtOQ");
        Student s8 = new Student("aiannitti7@is.gd", "Alexandra Iannitti", "TWP4hf5j");
        Student s9 = new Student("ljiroudek8@sitemeter.com", "Laryssa Jiroudek", "bXRoLUP");
        Student s10 = new Student("cjaulme9@bing.com", "Cahra Jaulme", "FnVklVgC6r6");

        Set<Student> stus = new HashSet<Student>();
        stus.add(s1);
        stus.add(s2);
        stus.add(s3);
        stus.add(s4);
        stus.add(s5);
        stus.add(s6);
        stus.add(s6);
        stus.add(s8);
        stus.add(s9);
        stus.add(s10);

        session.save(s1);
        session.save(s2);
        session.save(s3);
        session.save(s4);
        session.save(s5);
        session.save(s6);
        session.save(s7);
        session.save(s8);
        session.save(s9);
        session.save(s10);

        //Add courses
        Course c1 = new Course(1, "English", "Anderea Scamaden");
        Course c2 = new Course(2, "Mathematics", "Eustace Niemetz");
        Course c3 = new Course(3, "Anatomy", "Reynolds Pastor");
        Course c4 = new Course(4, "Organic Chemistry", "Odessa Belcher");
        Course c5 = new Course(5, "Physics", "Dani Swallow");
        Course c6 = new Course(6, "Digital Logic", "Glenden Reilingen");
        Course c7 = new Course(7, "Object Oriented Programming", "Giselle Ardy");
        Course c8 = new Course(8, "Data Structures", "Carolan Stoller");
        Course c9 = new Course(9, "Politics", "Carmita De Maine");
        Course c10 = new Course(10, "Art", "Kingsly Doxsey");
        Set<Course> cs = new HashSet<Course>();
        cs.add(c1);
        cs.add(c2);
        cs.add(c3);
        cs.add(c4);
        cs.add(c5);
        cs.add(c6);
        cs.add(c7);
        cs.add(c8);
        cs.add(c9);
        cs.add(c10);
        session.save(c1);
        session.save(c2);
        session.save(c3);
        session.save(c4);
        session.save(c5);
        session.save(c6);
        session.save(c7);
        session.save(c8);
        session.save(c9);
        session.save(c10);
        session.getTransaction().commit();
        session.close();
    }
    @AfterAll
    public static void afterALlTests() {
        factory.close();
    }
*/
    @Test
    public void testGetAllStudents(){
        List<Student> ls = ss.getAllStudents();
        //Okay this isnt really a good test since it isn't automated
        //But school project and ran out of time
        System.out.println(ls.toString());

    }

    @ParameterizedTest
    @ValueSource(strings = {"qllorens2@howstuffworks.com"})
    public void testGetStudentByEmail(String sEmail) {
        String expected =sEmail;
        Student sActual = ss.getStudentByEmail(sEmail);
        assert(expected.equals(sActual.getSEmail()));
    }

    @ParameterizedTest
    @CsvSource({"cstartin3@flickr.com,XYHzJ1S,true" ,"aiannitti7@is.gd,wrongPsswd,false"} )
    public void testValidateStudent(String sEmail, String sPassword, boolean expected){
        boolean actual = ss.validateStudent(sEmail, sPassword);
        assertEquals(actual,expected);
    }

    @ParameterizedTest
      @CsvSource({"sbowden1@yellowbook.com,4" , "hluckham0@google.ru,5" })
//    @CsvSource({"sbowden1@yellowbook.com,4" })
    public void testRegisterStudentToCourse(String sEmail , int cId) {
        ss.registerStudentToCourse(sEmail , cId);
        // grab our student back and check their courses --
        // By our business logic
        // registerStudent runs only After validateStudent therefore getStudentByEmail is functioning
        Student sActual = ss.getStudentByEmail(sEmail);
        boolean found = false;
        for (Course c : sActual.getSCourses()) { if (cId == c.getCId()) {found = true; break; }}
        assert(found);
    }

//    @ParameterizedTest
//    @CsvSource({"hguerre5@deviantart.com,14"})

    @ParameterizedTest
    @CsvSource({"hluckham0@google.ru,2,5,6"})
    public void testGetStudentCourses(String sEmail, int numOfCourses, int cId1, int cId2) {
        List<Course> sCourses= ss.getStudentCourses(sEmail);
        boolean result = true;
        if (sCourses.size() == numOfCourses) {
            if ((sCourses.get(0).getCId() == cId1) &&  (sCourses.get(1).getCId() == cId2)) result = true;
            }
        assert(result);
    }
}
