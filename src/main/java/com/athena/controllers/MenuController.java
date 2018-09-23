package com.athena.controllers;

import com.athena.model.*;
import com.athena.services.CategoryService;
import com.athena.services.ItemService;
import com.athena.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Scope("session")
public class MenuController
{
    private List<CategoryDTO> allCats;
    private Bucket bucket;
    private ItemDTO selectedItem = new ItemDTO();

    private CategoryService categoryService;
    private ItemService itemService;
    private MenuService menuService;

    @Autowired
    public MenuController(CategoryService categoryService, ItemService itemService, MenuService menuService)
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
            selectedItem = new ItemDTO();
        else
            selectedItem = menuService.getItemById(allCats, id);
    }

    @RequestMapping("/menu")
    public String menu(Model model)
    {
        if(allCats == null)
        {
            initialize();
            //populateFeed();
        }

        model.addAttribute("allCats", allCats);
        model.addAttribute("selectedItem", selectedItem);
        model.addAttribute("bucket", bucket);
        model.addAttribute("totalItems", bucket.getEntries().size());

        return "menu";
    }

    @RequestMapping("/item/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void item(@PathVariable("id") String id,Model model)
    {
        checkSelectedItem(id);
        model.asMap().replace("selectedItem", selectedItem);
//        int x;
//        return "redirect:/menu";
    }

}
