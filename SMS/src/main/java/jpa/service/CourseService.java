package jpa.service;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CourseService implements CourseDAO {
    @Override
    public List<Course> getAllCourses() {
        SessionFactory factory = smsGetSessionFactory.getFactory();
        Session session = factory.openSession();
        List<Course> rsList = session.createQuery("from Course c").list();
        session.close();
        Collections.sort(rsList);
        return rsList;
    }



    public Course getCourseById(int cId) {
        Course course = null;
        SessionFactory factory = smsGetSessionFactory.getFactory();
        Session session = factory.openSession();
        String sqlgetCourseBycId = "SELECT c From Course c WHERE c.cId = :cId";
        Query query = session.createQuery(sqlgetCourseBycId);
        query.setParameter("cId", cId);
        try {
            course = (Course) query.getSingleResult();
        } catch ( NoResultException nre ) {
            System.out.println("No Result Exception trying to obtain course by id =" + cId);
            System.out.println(nre.getMessage());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }
        return course;
    }
}
