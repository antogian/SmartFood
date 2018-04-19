package com.athena.controllers;

import com.athena.entities.Menu;
import com.athena.entities.Product;
import com.athena.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    private Product getProductById(String id)
    {
        for(int i = 0; i<allMenus.size(); i++)
        {
            for(int j=0; j<allMenus.get(i).getProductList().size(); j++)
            {
                if (allMenus.get(i).getProductList().get(j).getId().equals(id))
                {
                    return allMenus.get(i).getProductList().get(j);
                }
            }
        }
        return new Product();
    }

    private void addItemToBucket(String id)
    {
        for(int i = 0; i<allMenus.size(); i++)
        {
            for(int j=0; j<allMenus.get(i).getProductList().size(); j++)
            {
                if (allMenus.get(i).getProductList().get(j).getId().equals(id))
                {
                    bucket.add(allMenus.get(i).getProductList().get(j));
                    return;
                }
            }
        }
    }

    private void addItemToBucket(Product item)
    {
        bucket.add(item);
    }

    private void removeItemFromBucket(String id)
    {
        for(int i = 0; i< bucket.size(); i++)
        {
            if (bucket.get(i).getId().equals(id))
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
        model.addAttribute("bucket", bucket);
        model.addAttribute("totalItems", bucket.size());
        model.addAttribute("selectedProduct", new Product());

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

        return "menu";
    }

    @RequestMapping(value="/addItemToCart", method=RequestMethod.POST)
    public String addItemToCart(@RequestParam(value="productID") String id,
                                @RequestParam(value="checkedToppings", required=false) List<String> toppings)
    {
        Product newItem = getProductById(id);
        newItem.setSelectedToppings(toppings);
        addItemToBucket(newItem);

        return "redirect:/menu";
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

        return "redirect:/menu";
    }

    @RequestMapping("/experiments")
    public String experiments(Model model)
    {
        if(allMenus == null && bucket == null)
        {
            initializeFeed();
            //populateFeed();
        }
        model.addAttribute("allMenus", allMenus);
        model.addAttribute("totalItems", bucket.size());

        return "experiments";
    }

}
