package com.test.hib.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.test.hib.model.User;

public class UpdatingUser {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        /*User u = new User(); */
        int USER_ID = 3;
        User u = session.load(User.class, USER_ID);
        /* u.setId(3); */
        u.setEmail("mhaseeb@perscholas");
        u.setFullname("M haseeb");
        u.setPassword("123456");
        session.merge(u);
        session.getTransaction().commit();
        session.close();
    }

}
