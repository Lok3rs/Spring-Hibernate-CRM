package com.codecool.hibernateutil;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class HibernateUtil {


    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // will work if start directly from main app
                InputStream input = new FileInputStream("application.properties");
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
//                settings.put(Environment.DRIVER, "org.postgresql.Driver");
//                settings.put(Environment.URL, "jdbc:postgresql://localhost/many-to-many");
//                settings.put(Environment.USER, "postgres");
//                settings.put(Environment.PASS, "z3xjek39");
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
//
//                settings.put(Environment.SHOW_SQL, "true");
//
//                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//
//                settings.put(Environment.HBM2DDL_AUTO, "update");

                settings.load(input);
                configuration.setProperties(settings);


                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
