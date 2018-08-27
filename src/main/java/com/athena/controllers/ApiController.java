package com.athena.controllers;

import com.athena.entities.Category;
import com.athena.entities.Item;
import com.athena.entities.Order;
import com.athena.entities.Product;
import com.athena.model.CategoryDTO;
import com.athena.services.CategoryService;
import com.athena.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Autowired
    private OrderService orderService;

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
    public List<Product> getApi()
    {
        initialize();

        List<Product> allProducts = new ArrayList<>();
        allProducts = orderService.selectAllOrders();

//        List<Item> allItems = new ArrayList<>();
//
//        allItems.add( allCats.get(1).getAllItems().get(5));
//        allItems.add( allCats.get(3).getAllItems().get(5));
//        allItems.add( allCats.get(5).getAllItems().get(5));
//        allItems.add( allCats.get(7).getAllItems().get(5));

        return allProducts;
    }
}
