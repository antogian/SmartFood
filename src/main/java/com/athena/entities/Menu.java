package com.athena.entities;

import java.util.ArrayList;
import java.util.List;

public class Menu
{
    private String name;
    private List<Product> productList;

    public Menu()
    {
        this.name = "";
        this.productList = new ArrayList<Product>();
    }

    public Menu(String name, List<Product> productList)
    {
        this.name = name;
        this.productList = productList;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Product> getProductList()
    {
        return productList;
    }

    public void setProductList(List<Product> productList)
    {
        this.productList = productList;
    }
}
