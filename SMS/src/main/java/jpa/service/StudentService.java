package jpa.service;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class StudentService implements StudentDAO {
    public StudentService() {
    }

    @Override
    public List<Student> getAllStudents() {
        SessionFactory factory = smsGetSessionFactory.getFactory();
        Session session = factory.openSession();
        List<Student> rsList = null;
        try {
 //           @SuppressWarnings("unchecked")  //Wow Hibernate docs actually say to do this because of the List cast
            rsList = (List<Student>) session.createQuery("from Student s").list();
            Collections.sort(rsList);
            } catch (HibernateException he) {
            System.out.println("%n ** Error obtain student List **  Please logout%n");
            System.out.println(he.getMessage());

        } finally {
            session.close();
        }
        return rsList;
    }

    @Override
    public Student getStudentByEmail(String sEmail) {
        Student result = null;
        String sqlgetByEmail = "SELECT s From Student s WHERE s.sEmail = :email";
        //       String sqlgetByEmail = "SELECT s from Student s WHERE s.sEmail = '"+sEmail+"'";  //Need this to debug
        if (sEmail != null && !(sEmail.isEmpty())) {
            SessionFactory factory = smsGetSessionFactory.getFactory();
            Session session = factory.openSession();
            Query query = session.createQuery(sqlgetByEmail);
            query.setParameter("email", sEmail);
            try {
                result = (Student) query.getSingleResult();
                return result;
            } catch ( NoResultException nre ) {
                System.out.println("No Student found with Email =" + sEmail + "");
                System.out.println(nre.getMessage());
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            finally {
                session.close();
            }

        }
        return result;
    }

    @Override
    public boolean validateStudent(String sEmail, String sPassword) {
        if (!(sEmail.isEmpty()) && !(sPassword.isEmpty())) {
            Student student = getStudentByEmail(sEmail);
            if (student != null) {
                if (sPassword.equals(student.getSPass())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void registerStudentToCourse(String sEmail, int cId) {
        //Hibernate is maintaining the student_course table automatically with EAGER,
        //Only need to retrieve the student and check the courses set.
        Student student = getStudentByEmail(sEmail);
        Set<Course> sCourses = student.getSCourses();
        boolean found = false;
        Course course = null;
        if (sCourses.size() > 0) {
            for (Course c : sCourses) {
                if (c.getCId() == cId) {
                    found = true;
//                    System.out.println("Found " + sEmail + " in course " + cId);
                    break;
                }
            }
        }
        if (!found) {
            course = smsGetCourseService.getCourseService().getCourseById(cId);
            if (course == null) return;
//                //Retrieve the course  -- which really should be done by the CourseService object /sigh
//                  SessionFactory factory = smsGetSessionFactory.getFactory();
//                  Session session = factory.openSession();
//                String sqlgetCourseBycId = "SELECT c From Course c WHERE c.cId = :cId";
//                Query query = session.createQuery(sqlgetCourseBycId);
//                query.setParameter("cId", cId);
//                try {
//                    course = (Course) query.getSingleResult();
//                } catch (Exception e) {
//                    return;
//                }
//                finally {
//                    session.close();
//                }


            //Start a transaction , add the course in, update , commit, cleanup
            //And this is where using Hibernate sessionFactory sematics bit me in the a**
            //change this over to entityManager please.
            SessionFactory factory = smsGetSessionFactory.getFactory();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            try {
//               session.merge(student);
                student.getSCourses().add(course);
                session.update(student);
                tx.commit();
            } catch (Exception e) {
                System.out.println("Exception trying to update Student " + sEmail + "with course id =" + cId);
                System.out.println(e.getMessage());
                tx.rollback();
            } finally {
                session.close();
            }
        } //if (!found)
    } //resisterStudentToCourse

    @Override
    public List<Course> getStudentCourses(String sEmail) {
        Student student = getStudentByEmail(sEmail);
        Set<Course> sc = student.getSCourses();
        List<Course> rl = null;
        if (sc != null) { // sort set and turn into a list
            rl = new ArrayList<Course>(sc);
            Collections.sort(rl);
        }
        else {
            //do something here
        }
        return rl;
    }
}
