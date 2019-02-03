package com.athena.dao;

import com.athena.entities.Category;
import com.athena.entities.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDAO
{
    List<Order> allOrders = new ArrayList<>();


    public OrderDAO()
    {}

    public void insertOrder(Order newOrder)
    {
//        Gson gson = new Gson();
//        ClassLoader classLoader = getClass().getClassLoader();
//        InputStream in = classLoader.getResourceAsStream("data/orders.json");
//        JsonReader reader = new JsonReader(new InputStreamReader(in));
//        JsonWriter writer = new JsonWriter();
//        List<Category> allCategories = gson.fromJson(reader, new TypeToken<List<Category>>(){}.getType());
//        try
//        {
//            gson.toJson(newOrder, "data/orders.json"); //error
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
        allOrders.add(newOrder);
    }

    public List<Order> getAllOrders()
    {
        return this.allOrders;
    }
}
