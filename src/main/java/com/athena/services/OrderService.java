package com.athena.services;

import com.athena.dao.OrderDAO;
import com.athena.entities.Order;
import com.athena.entities.User;
import com.athena.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService
{
    private OrderDAO orderDAO;

    @Autowired
    public OrderService(OrderDAO orderDAO)
    {
        this.orderDAO = orderDAO;
    }

    public void insertOrder(Order newOrder)
    {
        orderDAO.insertOrder(newOrder);
    }

    public List<Order> getAllOrders()
    {
        return orderDAO.getAllOrders();
    }

    public void setOrderInfo(User currentUser, ShoppingCart cart)
    {
        Order newOrder = new Order();
        newOrder.setUser(currentUser);
        newOrder.setCart(cart);
    }
}
