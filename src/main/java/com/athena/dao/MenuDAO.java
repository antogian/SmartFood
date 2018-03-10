package com.athena.dao;

import com.athena.entities.FoodCollection;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuDAO
{
    public MenuDAO() { }

    public List<FoodCollection> getMenu()
    {
        List<FoodCollection> menu = new ArrayList<FoodCollection>();
        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "In quis arcu tristique, luctus risus ac, imperdiet velit. " +
                "Maecenas fermentum felis quis laoreet tristique.";
        menu.add(new FoodCollection("1", "Pizza", description, 15.99));
        menu.add(new FoodCollection("2", "Cheeseburger", description, 7.99));
        menu.add(new FoodCollection("3", "Pancakes", description, 5.99));
        menu.add(new FoodCollection("4", "French fries", description, 8.99));
        menu.add(new FoodCollection("5", "Croissant", description, 1.99));
        menu.add(new FoodCollection("6", "Stake", description, 5.99));
        menu.add(new FoodCollection("7", "Chicken", description, 6.99));
        menu.add(new FoodCollection("8", "Ice-cream", description, 0.99));
        menu.add(new FoodCollection("9", "Souvlaki", description, 3.99));

        return menu;
    }
}
