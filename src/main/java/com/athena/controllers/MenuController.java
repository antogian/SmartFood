package com.athena.controllers;

import com.athena.entities.Menu;
import com.athena.entities.Product;
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
    private List<Menu> allMenus;
    private List<Product> bucket;

    @Autowired
    MenuService menuService;

    private void initializeFeed()
    {
        allMenus = new ArrayList<Menu>();
        allMenus = menuService.getAllMenus();
        bucket = new ArrayList<Product>();
    }

    private void addItemToBucket(String id)
    {
        for(int i = 0; i<allMenus.size(); i++)
        {
            for(int j=0; j<allMenus.get(i).getProductList().size(); j++)
            {
                if (allMenus.get(i).getProductList().get(j).getProductId().equals(id))
                {
                    bucket.add(allMenus.get(i).getProductList().get(j));
                    return;
                }
            }
        }
    }

    private void removeItemFromBucket(String id)
    {
        for(int i = 0; i< bucket.size(); i++)
        {
            if (bucket.get(i).getProductId().equals(id))
            {
                bucket.remove(i);
                return;
            }
        }
    }

    @RequestMapping("/menu")
    public String menu(Model model)
    {
        if(allMenus == null && bucket == null)
        {
            initializeFeed();
            //populateFeed();
        }
        model.addAttribute("allMenus", allMenus);
        model.addAttribute("totalItems", bucket.size());

        return "menu";
    }

    @RequestMapping("/bucket")
    public String bucket(Model model)
    {
        if (bucket == null)
        {
            bucket = new ArrayList<Product>();
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
