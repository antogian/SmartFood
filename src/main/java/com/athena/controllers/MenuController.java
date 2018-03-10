package com.athena.controllers;

import com.athena.entities.FoodCollection;
import com.athena.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    MenuService menuService;

    private void initializeFeed()
    {
        menu = new ArrayList<FoodCollection>();
        menu = menuService.getMenu();
        bucket = new ArrayList<FoodCollection>();
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
            //populateFeed();
        }
        model.addAttribute("menu", menu);
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
