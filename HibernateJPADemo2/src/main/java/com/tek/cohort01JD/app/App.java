package com.tek.cohort01JD.app;

import com.tek.cohort01JD.model.DepartmentOtM;
import com.tek.cohort01JD.model.Teacher;
import com.tek.cohort01JD.model.TeacherOtM;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

//        Department dep = new Department();
//        dep.setDeptname("IT");
//
//        Department dep2 = new Department();
//        dep2.setDeptname("HR");

        TeacherOtM t1 = new TeacherOtM();
//        t1.setDep(dep);
        t1.setSalary("1000");
        t1.setTeachername("MHaseeb");

        TeacherOtM t2 = new TeacherOtM();
//        t2.setDep(dep);
        t2.setSalary("2220");
        t2.setTeachername("Shahparan");

        TeacherOtM t3 = new TeacherOtM();
//        t3.setDep(dep);
        t3.setSalary("30000");
        t3.setTeachername("James");

        TeacherOtM t4 = new TeacherOtM();
//        t3.setDep(dep2);
        t4.setSalary("40000");
        t4.setTeachername("Joseph");

        TeacherOtM t5 = new TeacherOtM();
        t5.setSalary("20000");
        t5.setTeachername("Ali");


//Add teacher entity object to the list
        List<TeacherOtM> teachlist = new ArrayList<>();
        teachlist.add(t1);
        teachlist.add(t2);
        teachlist.add(t3);
        teachlist.add(t4);
//        teachlist.add(t5);
        session.save(t1);
        session.save(t2);
        session.save(t3);
        session.save(t4);
//        session.save(t5);
        DepartmentOtM dep = new DepartmentOtM();
        dep.setDeptname("Development");
        dep.setTeacherList(teachlist);
        session.save(dep);

        List<TeacherOtM> teachlist2 = new ArrayList<>();
/*        teachlist.clear(); */
        teachlist2.add(t5);
        session.save(t5);
        DepartmentOtM dep2 = new DepartmentOtM();
        dep2.setDeptname("Arts");
        dep2.setTeacherList(teachlist2);
        session.save(dep2);
        t.commit();

    }

}

