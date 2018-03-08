package com.athena.controllers;

import com.athena.model.entities.FoodCollection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController
{
    private List<FoodCollection> menu;
    private List<FoodCollection> bucket;

    private void initializeFeed()
    {
        menu = new ArrayList<FoodCollection>();
        bucket = new ArrayList<FoodCollection>();
    }

    private void populateFeed()
    {
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
    }

    private void addItemToBucket(String id)
    {
        for(int i = 0; i< menu.size(); i++)
        {
            if (menu.get(i).getFoodId().equals(id))
            {
                bucket.add(menu.get(i));
            }
        }
    }

    private void removeItemFromBucket(String id)
    {
        for(int i = 0; i< menu.size(); i++)
        {
            if (menu.get(i).getFoodId().equals(id))
            {
                bucket.remove(menu.get(i));
            }
        }
    }

    @RequestMapping("/menu")
    public String menu(Model model)
    {
        if(menu == null && bucket == null)
        {
            initializeFeed();
            populateFeed();
        }
        model.addAttribute("foods", menu);
        model.addAttribute("totalItems", bucket.size());

        return "menu";
    }

    @RequestMapping("/bucket")
    public String bucket(Model model)
    {
        if (bucket == null)
        {
            bucket = new ArrayList<FoodCollection>();
        }
        model.addAttribute("bucket", bucket);
        model.addAttribute("totalItems", bucket.size());

        return "bucket";
    }

    @RequestMapping({"/addItem"})
    public String addItem(@RequestParam(value = "code") String code)
    {
        addItemToBucket(code);

        return "redirect:/menu";
    }

    @RequestMapping({"/removeItem"})
    public String removeItem(@RequestParam(value = "code") String code)
    {
        removeItemFromBucket(code);

        return "redirect:/bucket";
    }

}
