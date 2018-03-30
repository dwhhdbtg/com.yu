package com.yu.utils;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static final Configuration cfg;
    private static final SessionFactory sessionFactory;
    static{
            cfg=new Configuration().configure();
            sessionFactory=cfg.buildSessionFactory();
    }
    // 提供一个静态的方法获得session对象.
    public static Session getSession(){
        return sessionFactory.openSession();
    }
}
