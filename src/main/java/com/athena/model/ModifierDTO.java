package com.athena.model;

import com.athena.entities.ModEntry;

import java.util.List;

public class ModifierDTO
{
    private String name;
    private List<ModEntryDTO> entries;
    private boolean isPlatter = false;
    private double platterPrice = 0.0;
    private boolean halfEnabled = false;
    private boolean qualifiersEnabled = true;
    private int freeEntries;
    private int requiredEntries;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<ModEntryDTO> getEntries()
    {
        return entries;
    }

    public void setEntries(List<ModEntryDTO> entries)
    {
        this.entries = entries;
    }

    public boolean isPlatter()
    {
        return isPlatter;
    }

    public void setPlatter(boolean platter)
    {
        isPlatter = platter;
    }

    public double getPlatterPrice()
    {
        return platterPrice;
    }

    public void setPlatterPrice(double platterPrice)
    {
        this.platterPrice = platterPrice;
    }

    public boolean isHalfEnabled()
    {
        return halfEnabled;
    }

    public void setHalfEnabled(boolean halfEnabled)
    {
        this.halfEnabled = halfEnabled;
    }

    public boolean isQualifiersEnabled()
    {
        return qualifiersEnabled;
    }

    public void setQualifiersEnabled(boolean qualifiersEnabled)
    {
        this.qualifiersEnabled = qualifiersEnabled;
    }

    public int getFreeEntries()
    {
        return freeEntries;
    }

    public void setFreeEntries(int freeEntries)
    {
        this.freeEntries = freeEntries;
    }

    public int getRequiredEntries()
    {
        return requiredEntries;
    }

    public void setRequiredEntries(int requiredEntries)
    {
        this.requiredEntries = requiredEntries;
    }
}
