package jpa.service;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class smsGetSessionFactory {

    private static SessionFactory factory = null;

    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException he) {
            System.out.println("*** COULD NOT OPEN DATABASE ***");
            System.out.println(he.getMessage());
            factory.close();
        }
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}
