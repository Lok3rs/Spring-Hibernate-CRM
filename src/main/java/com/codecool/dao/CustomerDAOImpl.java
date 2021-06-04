package com.codecool.dao;

import com.codecool.entity.Customer;
import com.codecool.hibernateutil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public List<Customer> getCustomers(int sortField) {
        String fieldName;
        switch (sortField) {
            case 1 -> fieldName = "firstName";
            case 3 -> fieldName = "email";
            default -> fieldName = "lastName";
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        List<Customer> customers = new ArrayList<>();
        try {
            currentSession.beginTransaction();
            customers = currentSession.createQuery("from Customer order by " + fieldName, Customer.class).getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        try {
            currentSession.beginTransaction();
            currentSession.saveOrUpdate(customer);
            currentSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getCustomer(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        Customer customer = null;
        try {
            currentSession.beginTransaction();
            customer = currentSession.get(Customer.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        try {
            currentSession.beginTransaction();
            Customer customer = currentSession.get(Customer.class, id);
            currentSession.delete(customer);
            currentSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> searchCustomers(String searchPhrase) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        List<Customer> customers = new ArrayList<>();
        try {
            currentSession.beginTransaction();
            Query<Customer> query;
            if (searchPhrase != null && searchPhrase.trim().length() > 0) {
                query = currentSession.createQuery("from Customer where lower(firstName) like :searchPhrase or " +
                        "lower(lastName) like :searchPhrase or " +
                        "lower(email) like :searchPhrase", Customer.class);
                query.setParameter("searchPhrase", "%" + searchPhrase.toLowerCase() + "%");
            } else {
                query = currentSession.createQuery("from Customer order by lastName", Customer.class);
            }
            customers = query.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }
}
