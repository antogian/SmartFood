package com.athena.entities;

public class ModEntry
{
    private String name;
    private int index;
    private double cost;

    public ModEntry()
    {
        name = "";
        cost = 0.00;
    }

    public ModEntry(String name, double cost)
    {
        this.name = name;
        this.cost = cost;
    }

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

    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }
}
