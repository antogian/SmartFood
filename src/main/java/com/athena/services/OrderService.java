package com.athena.services;

import com.athena.dao.ProductDAO;
import com.athena.entities.Order;
import com.athena.entities.Product;
import com.athena.entities.User;
import com.athena.model.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService
{
    @Autowired
    ProductDAO productDAO;

    public OrderService()
    {

    }

    public void insertOrder(Order newOrder)
    {
        //productDAO.insertProducts(newOrder.getCart().getSelectedProducts());
    }

    public List<Product> selectAllOrders()
    {
        List<Product> allProducts = new ArrayList<>();
        allProducts = productDAO.selectAllProducts();
        return allProducts;
    }

    public void setOrderInfo(User currentUser, Bucket cart)
    {
        Order newOrder = new Order();
        newOrder.setUser(currentUser);
        newOrder.setCart(cart);
    }
}
