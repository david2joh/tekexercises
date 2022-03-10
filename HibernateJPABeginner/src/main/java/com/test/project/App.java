package com.test.project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.test.hib.controller.findUser_Hql;
import com.test.hib.model.User;
public class App {
    public static void main(String[] args) {

        findUser_Hql u = new findUser_Hql();
        // Find/Sect * all users
        u.findUser();
        //Find/Select * all users with a iteration over the returned list of users
        u.findUserSelect();
        //Find/Select * from users where userID < 5
        u.getRecordbyId();
        //Determine the max salary of users
        u.getmaxSalary();
        //Find how many users we have
        u.getnumUsers();
        //Basic Named Query example , for now getting userID == 2
        u.NamedQueryExample();

    }

}
