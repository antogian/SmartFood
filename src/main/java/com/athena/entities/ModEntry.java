package com.athena.entities;

public class ModEntry
{
    private String name;
    private int index;
    private double[] cost;
    private double[] halfCost;

    public ModEntry()
    {
        name = "";
    }

    public ModEntry(String name)
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double[] getCost()
    {
        return cost;
    }

    public void setCost(double[] cost)
    {
        this.cost = cost;
    }

    public double[] getHalfCost()
    {
        return halfCost;
    }

    public void setHalfCost(double[] halfCost)
    {
        this.halfCost = halfCost;
    }
}
