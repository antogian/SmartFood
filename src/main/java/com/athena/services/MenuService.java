package com.athena.services;

import com.athena.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService
{
    private CategoryService categoryService;

    @Autowired
    public MenuService(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    public ItemDTO getItemById(List<CategoryDTO> allCats, String id)
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

    public void removeItemById(ShoppingCart shoppingCart, String name)
    {
        shoppingCart.removeEntryById(name);
    }

    public void checkModifiers(ItemDTO item, List<ModifierDTO> modifiers)
    {
        List<ModEntryDTO> selectedEntries = new ArrayList<>();
        for (ModifierDTO mod : modifiers)
        {
            for (ModEntryDTO entry : mod.getEntries())
            {
                if (entry.isSelected())
                    selectedEntries.add(entry);
            }
        }
        for (ModifierDTO mod : item.getModifiers())
        {
            for (ModEntryDTO entry : mod.getEntries())
            {
                entry.setSelected(false);
                for(ModEntryDTO selectedEntry : selectedEntries)
                {
                    if(entry.getName().equalsIgnoreCase(selectedEntry.getName()))
                    {
                        entry.setSelected(selectedEntry.isSelected());
                        entry.setHalfOption(selectedEntry.getHalfOption());
                        entry.setQualifier(selectedEntry.getQualifier());
                    }
                }
            }
        }
    }

    public void checkSizes(ItemDTO item, String name)
    {
        if(item.getAllSizes() == null)
            return;

        for(SizeDTO size : item.getAllSizes())
        {
            if(size.getName().equalsIgnoreCase(name))
                size.setSelected(true);
            else
                size.setSelected(false);
        }
    }

    public CartEntry getEntryFromCart(ShoppingCart shoppingCart, String id)
    {
        for (CartEntry entry : shoppingCart.getEntries())
        {
            if(id.equalsIgnoreCase(entry.getItem().getId()))
            {
                return entry;
            }
        }
        return new CartEntry();
    }


}
