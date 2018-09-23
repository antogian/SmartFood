package com.athena.controllers;

import com.athena.model.*;
import com.athena.services.CategoryService;
import com.athena.services.ItemService;
import com.athena.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Scope("session")
public class CategoryController
{
    private List<CategoryDTO> allCats;
    private Bucket bucket;
    private ItemDTO currentItem = new ItemDTO();

    private CategoryService categoryService;
    private ItemService itemService;
    private MenuService menuService;

    @Autowired
    public CategoryController(CategoryService categoryService, ItemService itemService, MenuService menuService)
    {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.menuService = menuService;
    }

    private void initialize()
    {
        bucket = new Bucket();
        allCats = new ArrayList<>();
        try
        {
            allCats = categoryService.getAllCats();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void checkSelectedItem(String id)
    {
        if(id == null || id.equalsIgnoreCase(""))
            currentItem = new ItemDTO();
        else
            currentItem = menuService.getItemById(allCats, id);
    }

    @RequestMapping("/data")
    public String menu(Model model)
    {
        try
        {
            if(allCats == null)
            {
                initialize();
            }

            model.addAttribute("allCats", allCats);
            model.addAttribute("bucket", bucket);
            model.addAttribute("totalItems", bucket.getEntries().size());

            return "/data";
        }
        catch(Exception e)
        {
            model.addAttribute("message", e.getMessage());
            return "/error";
        }
    }

    @RequestMapping("/data/item/{id}")
    public String item(@PathVariable("id") String id, Model model)
    {
        checkSelectedItem(id);
        model.addAttribute("bucket", bucket);
        model.addAttribute("totalItems", bucket.getEntries().size());
        model.addAttribute("currentItem", currentItem);
        model.addAttribute("modifiers", currentItem.getModifiers());

        return "item";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String addItemToCart(@RequestParam(value="itemId") String id,
                                @ModelAttribute("currentItem") ItemDTO item,
                                @RequestParam(value="itemQuantity") int quantity,
                                @RequestParam(value="itemSize", required = false) String sizeName)
    {
        ItemDTO newItem = menuService.getItemById(allCats, id);
        ItemDTO bucketItem = itemService.getItemByValue(newItem);
        bucketItem.setId(UUID.randomUUID().toString());

        BucketEntry newEntry = new BucketEntry();

        //TODO: Correct no toppings scenario
        menuService.checkModifiers(bucketItem, item.getModifiers());

        menuService.checkSizes(bucketItem, sizeName);
        bucketItem.calculateTotalCost();

        newEntry.setItem(bucketItem);
        newEntry.setQuantity(quantity);
        bucket.addEntry(newEntry);

        return "redirect:/data";
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public String editITem(@RequestParam(value="itemId") String id,
                           @ModelAttribute("currentItem") ItemDTO item,
                           @RequestParam(value="itemQuantity") int quantity,
                           @RequestParam(value="itemSize", required = false) String sizeName)
    {
        BucketEntry newEntry = menuService.getEntryFromCart(bucket, id);

        menuService.checkModifiers(newEntry.getItem(), item.getModifiers());
        newEntry.getItem().calculateTotalCost();
        menuService.checkSizes(newEntry.getItem(), sizeName);
        newEntry.setQuantity(quantity);

        bucket.removeEntryById(id);
        bucket.addEntry(newEntry);

        return "redirect:/data";
    }

    @RequestMapping("/data/item/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model)
    {
        BucketEntry newEntry = menuService.getEntryFromCart(bucket, id);
        currentItem = newEntry.getItem();
        //TODO: Quantity isn't initiated.
        model.addAttribute("bucket", bucket);
        model.addAttribute("totalItems", bucket.getEntries().size());
        model.addAttribute("currentItem", currentItem);
        model.addAttribute("modifiers", currentItem.getModifiers());

        return "edit";
    }

    @RequestMapping({"/delete"})
    public String removeItem(@RequestParam(value = "item") String id)
    {
        menuService.removeItemById(bucket, id);

        return "redirect:/data";
    }

    @RequestMapping(value="/proceed")
    public String checkout(HttpServletRequest request)
    {
        request.getSession().setAttribute("shoppingCart", bucket);

        return "checkout";
    }
}
