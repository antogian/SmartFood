package com.athena.dao;

import com.athena.entities.Menu;
import com.athena.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuDAO
{
    public MenuDAO() { }

    public List<Menu> getAllMenus()
    {
        /* List<Product> allPizza = new ArrayList<Product>();
        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "In quis arcu tristique, luctus risus ac, imperdiet velit. " +
                "Maecenas fermentum felis quis laoreet tristique.";
        allPizza.add(new Product("1", "Pizza", description, 15.99));
        allPizza.add(new Product("2", "Cheeseburger", description, 7.99));
        allPizza.add(new Product("3", "Pancakes", description, 5.99));
        allPizza.add(new Product("4", "French fries", description, 8.99));
        allPizza.add(new Product("5", "Croissant", description, 1.99));
        allPizza.add(new Product("6", "Stake", description, 5.99));
        allPizza.add(new Product("7", "Chicken", description, 6.99));
        allPizza.add(new Product("8", "Ice-cream", description, 0.99));
        allPizza.add(new Product("9", "Souvlaki", description, 3.99)); */

        List<Product> allPizzas = new ArrayList<Product>();
        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "In quis arcu tristique, luctus risus ac, imperdiet velit. " +
                "Maecenas fermentum felis quis laoreet tristique.";
        allPizzas.add(new Product("11", "Neapolitan Pizza", description, 6.99));
        allPizzas.add(new Product("12", "Chicago Pizza", description, 7.99));
        allPizzas.add(new Product("13", "New York Pizza", description, 5.99));
        allPizzas.add(new Product("14", "Sicilian Pizza", description, 8.99));
        allPizzas.add(new Product("15", "Greek Pizza", description, 9.99));
        allPizzas.add(new Product("16", "California Pizza", description, 5.99));
        Menu pizzaMenu = new Menu();
        pizzaMenu.setName("PIZZA");
        pizzaMenu.setProductList(allPizzas);

        List<Product> allBurgers = new ArrayList<Product>();
        allBurgers.add(new Product("21", "Hamburger", description, 6.99));
        allBurgers.add(new Product("22", "Cheeseburger", description, 7.99));
        allBurgers.add(new Product("23", "Barbecue burger", description, 5.99));
        allBurgers.add(new Product("24", "Chili burger", description, 8.99));
        allBurgers.add(new Product("25", "Veggie burger", description, 9.99));
        Menu burgerMenu = new Menu();
        burgerMenu.setName("BURGER");
        burgerMenu.setProductList(allBurgers);

        List<Product> allDrinks = new ArrayList<Product>();
        allDrinks.add(new Product("31", "Coca-Cola", description, 6.99));
        allDrinks.add(new Product("32", "Pepsi", description, 7.99));
        allDrinks.add(new Product("33", "Soda", description, 5.99));
        allDrinks.add(new Product("34", "Red Bull", description, 8.99));
        allDrinks.add(new Product("35", "Water", description, 9.99));
        Menu drinksMenu = new Menu();
        drinksMenu.setName("DRINKS");
        drinksMenu.setProductList(allDrinks);

        List<Menu> allMenus = new ArrayList<Menu>();
        allMenus.add(pizzaMenu);
        allMenus.add(burgerMenu);
        allMenus.add(drinksMenu);

        return allMenus;
    }
}
