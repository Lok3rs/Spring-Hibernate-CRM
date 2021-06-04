package com.codecool.dao;

import com.codecool.entity.Customer;
import com.codecool.hibernateutil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public List<Customer> getCustomers() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        List<Customer> customers = new ArrayList<>();
        try {
            currentSession.beginTransaction();
             customers = currentSession.createQuery("from Customer", Customer.class).getResultList();
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
            currentSession.save(customer);
            currentSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
