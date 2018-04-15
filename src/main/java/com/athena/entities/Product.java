package com.athena.entities;

import java.util.ArrayList;
import java.util.List;


public class Product
{
    private String id;
    private String name;
    private String description;
    private Double price;
    private List<String> toppings;

    private List<String> selectedToppings;

    public Product()
    {
        this.id = "";
        this.name = "";
        this.description = "";
        this.price = 0.0;
        this.toppings = new ArrayList<String>();
        this.selectedToppings = new ArrayList<String>();
    }

    public Product(String id, String name, String description, Double price, List<String> toppings)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.toppings = toppings;
        this.selectedToppings = new ArrayList<String>();
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public List<String> getToppings()
    {
        return toppings;
    }

    public void setToppings(List<String> toppings)
    {
        this.toppings = toppings;
    }

    public List<String> getSelectedToppings()
    {
        return selectedToppings;
    }

    public void setSelectedToppings(List<String> selectedToppings)
    {
        this.selectedToppings = selectedToppings;
    }
}
