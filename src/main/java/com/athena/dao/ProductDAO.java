package com.athena.dao;

import com.athena.entities.Product;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO
{
//    static Session sessionObj;
//
//
//    private static SessionFactory buildSessionFactory()
//    {
//        return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//    }
//
//    public void insertProducts(List<Product> allProducts)
//    {
//        sessionObj = buildSessionFactory().openSession();
//        sessionObj.beginTransaction();
//        for (Product prod: allProducts)
//        {
//            sessionObj.save(prod);
//        }
//        sessionObj.getTransaction().commit();
//        if(sessionObj != null)
//        {
//            sessionObj.close();
//        }
//    }
//
//    public List<Product> selectAllProducts()
//    {
//        sessionObj = null;
//        sessionObj = buildSessionFactory().openSession();
//        sessionObj.beginTransaction();
//        Query query = sessionObj.createQuery("from Product");
//
//        List<Product> allProducts = new ArrayList<>();
//        allProducts = query.list();
//
//        if(sessionObj != null)
//        {
//            sessionObj.close();
//        }
//
//        return allProducts;
//    }
}
