package com.athena.controllers;

import com.athena.entities.Order;
import com.athena.model.CartEntry;
import com.athena.model.CategoryDTO;
import com.athena.model.ShoppingCart;
import com.athena.services.CategoryService;
import com.athena.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController
{
    private List<CategoryDTO> allCats;

    @Autowired
    private CategoryService categoryService;
    private OrderService orderService;

    @Autowired
    public ApiController(OrderService orderService)
    {
        this.orderService = orderService;
    }

    private void initialize()
    {
        allCats = new ArrayList<>();
        try
        {
            allCats = categoryService.getAllCats();
        }
        catch(Exception e)
        {
            //
        }
    }

    @RequestMapping("/api")
    public List<Order> getApi()
    {
        initialize();

        List<ShoppingCart> allShoppingCarts = new ArrayList<>();
        ShoppingCart cart = new ShoppingCart();

        for(int i=0; i<10; i++)
        {
            CartEntry cartEntry = new CartEntry();
            cartEntry.setItem(allCats.get(i+1).getAllItems().get(i+2));
            cartEntry.setQuantity(1);
            cart.addEntry(cartEntry);
            allShoppingCarts.add(cart);
        }
//        List<Item> allItems = new ArrayList<>();
//
//        allItems.add( allCats.get(1).getAllItems().get(5));
//        allItems.add( allCats.get(3).getAllItems().get(5));
//        allItems.add( allCats.get(5).getAllItems().get(5));
//        allItems.add( allCats.get(7).getAllItems().get(5));


        return orderService.getAllOrders();

//        return allCats;
    }
}
