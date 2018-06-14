package com.athena.services;

import com.athena.entities.Order;
import com.athena.entities.User;
import com.athena.model.ShoppingCart;
import org.springframework.stereotype.Service;

@Service
public class OrderService
{
    public OrderService()
    {

    }

    public void setOrderInfo(User currentUser, ShoppingCart cart)
    {
        Order newOrder = new Order();
        newOrder.setUser(currentUser);
        newOrder.setCart(cart);
    }
}
