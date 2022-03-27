package jpa.entitymodels;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


public class ModelTest {
    public static void main(String[] args) {


        SessionFactory factory = new Configuration().configure().buildSessionFactory();
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

        Session session2 = factory.openSession();
        Transaction t2 = session2.beginTransaction();

        // Add some Students to courses
        Set<Course> s1courses = new HashSet<>();
        s1courses.add(c5);
        s1courses.add(c6);
        s1.setSCourses(s1courses);
        session2.merge(s1);
        session2.getTransaction().commit();
        System.out.println(s1.getSCourses());

        session2.close();
        factory.close();
        
    }
}