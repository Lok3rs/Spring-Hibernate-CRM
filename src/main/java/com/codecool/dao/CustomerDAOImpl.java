package com.codecool.dao;

import com.codecool.entity.Customer;
import com.codecool.hibernateutil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Override
    @Transactional
    public List<Customer> getCustomers() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();

        return currentSession.createQuery("from Customer as c", Customer.class).getResultList();

    }
}
