package com.athena.entities;

import com.athena.model.Bucket;
import com.athena.model.ShoppingCart;

public class Order
{
    private User user;
    private Bucket cart;

    public Order()
    {
        user = new User();
        cart = new Bucket();
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Bucket getCart()
    {
        return cart;
    }

    public void setCart(Bucket cart)
    {
        this.cart = cart;
    }
}
