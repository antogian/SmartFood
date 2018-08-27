package com.athena.model;

import com.athena.entities.Modifier;
import com.athena.entities.Size;

import java.util.List;

public class ItemDTO
{
    private String name;
    private String id;
    private int index;
    private double totalCost;
    private List<SizeDTO> allSizes;
    private List<ModifierDTO> modifiers;
    private int[] freeModEntries;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public List<ModifierDTO> getModifiers()
    {
        return modifiers;
    }

    public void setModifiers(List<ModifierDTO> modifiers)
    {
        this.modifiers = modifiers;
    }

    public int[] getFreeModEntries()
    {
        return freeModEntries;
    }

    public void setFreeModEntries(int[] freeModEntries)
    {
        this.freeModEntries = freeModEntries;
    }

    public List<SizeDTO> getAllSizes()
    {
        return allSizes;
    }

    public void setAllSizes(List<SizeDTO> allSizes)
    {
        this.allSizes = allSizes;
    }

    public double getTotalCost()
    {
        return totalCost;
    }

    public void setTotalCost(double totalCost)
    {
        this.totalCost = totalCost;
    }
}
