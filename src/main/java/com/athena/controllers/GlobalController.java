package com.athena.controllers;

import com.athena.model.ShoppingCart;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(assignableTypes={CheckoutController.class})
public class GlobalController
{
    /*
    private ShoppingCart cart;

    @ModelAttribute("shoppingCart")
    public ShoppingCart getCart()
    {
        return cart;
    }
    */
}
