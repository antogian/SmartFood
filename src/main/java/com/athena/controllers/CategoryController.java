package com.athena.controllers;

import com.athena.model.*;
import com.athena.services.CategoryService;
import com.athena.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Scope("session")
public class CategoryController
{
    private List<CategoryDTO> allCats;
    private Bucket bucket;

    @Autowired
    CategoryService categoryService;
    @Autowired
    ItemService itemService;

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

    private ItemDTO getItemById(String id)
    {
        for (int i=0; i<allCats.size(); i++)
        {
          for(int j=0; j<allCats.get(i).getAllItems().size(); j++)
          {
              if(id.equalsIgnoreCase(allCats.get(i).getAllItems().get(j).getId()))
              {
                  return allCats.get(i).getAllItems().get(j);
              }
          }
        }
        return new ItemDTO();
    }

    private BucketEntry getEntryFromCart(String id)
    {
        for (BucketEntry entry : bucket.getEntries())
        {
            if(id.equalsIgnoreCase(entry.getItem().getId()))
            {
                return entry;
            }
        }
        return new BucketEntry();
    }

    private void checkModifiers(ItemDTO item, List<String> names)
    {
        for(ModifierDTO mod : item.getModifiers())
        {
            for(ModEntryDTO entry : mod.getEntries())
            {
                if(names.contains(entry.getName()))
                {
                    entry.setIncluded(true);
                }
                else
                {
                    entry.setIncluded(false);
                }
            }
        }
    }

    private void removeItemById(String name)
    {
        bucket.removeEntryById(name);
    }

    @RequestMapping("/data")
    public String menu(Model model)
    {
        if(allCats == null)
        {
            initialize();
            //populateFeed();
        }
        model.addAttribute("allCats", allCats);
        model.addAttribute("bucket", bucket);
        model.addAttribute("totalItems", bucket.getEntries().size());

        return "data";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String addItemToCart(@RequestParam(value="itemId") String id,
                                @RequestParam(value="modifiers", required=false) List<String> modEntries,
                                @RequestParam(value="itemQuantity") int quantity)
    {
        ItemDTO newItem = getItemById(id);
        ItemDTO bucketItem = itemService.getItemByValue(newItem);
        bucketItem.setId(UUID.randomUUID().toString());

        BucketEntry newEntry = new BucketEntry();

        if(!(modEntries == null || modEntries.isEmpty()))
            checkModifiers(bucketItem, modEntries);

        newEntry.setItem(bucketItem);
        newEntry.setQuantity(quantity);
        bucket.addEntry(newEntry);

        return "redirect:/data";
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public String editITem(@RequestParam(value="itemId") String id,
                                @RequestParam(value="modifiers", required=false) List<String> modEntries,
                                @RequestParam(value="itemQuantity") int quantity)
    {
        BucketEntry newEntry = getEntryFromCart(id);

        if(!(modEntries == null || modEntries.isEmpty()))
            checkModifiers(newEntry.getItem(), modEntries);

        newEntry.setQuantity(quantity);

        return "redirect:/data";
    }

    @RequestMapping({"/delete"})
    public String removeItem(@RequestParam(value = "item") String id)
    {
        removeItemById(id);

        return "redirect:/data";
    }
}
