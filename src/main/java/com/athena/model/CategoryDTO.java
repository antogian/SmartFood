package com.athena.model;

import com.athena.entities.Item;

import java.util.List;

public class CategoryDTO
{
    private String name;
    private int index;
    private List<ItemDTO> allItems;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public List<ItemDTO> getAllItems()
    {
        return allItems;
    }

    public void setAllItems(List<ItemDTO> allItems)
    {
        this.allItems = allItems;
    }
}
