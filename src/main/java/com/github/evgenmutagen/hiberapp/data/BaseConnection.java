package com.github.evgenmutagen.hiberapp.data;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BaseConnection {
    private static SessionFactory sessionFactory;

    public static SessionFactory getInstance() {
        if (sessionFactory == null) {
            synchronized (BaseConnection.class) {
                if (sessionFactory == null) {
                    sessionFactory = new Configuration().configure().buildSessionFactory();
                }
            }
        }
        return sessionFactory;
    }
}
