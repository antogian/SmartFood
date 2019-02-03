package com.athena.model;

public class ModEntryDTO
{
    private String name;
    private int index;
    private double[] cost;
    private double[] halfCost;
    private boolean included = false;
    private boolean selected = false;
    private String halfOption = "Full";
    private String qualifier = "";

    public ModEntryDTO()
    {
        name = "";
    }

    public ModEntryDTO(String name, double cost)
    {
        this.name = name;
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

    public boolean isIncluded()
    {
        return included;
    }

    public void setIncluded(boolean included)
    {
        if(included)
        {
            this.selected = true;
            this.setCost(new double[5]);
            this.setHalfCost(new double[5]);
        }
        this.included = included;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public String getHalfOption()
    {
        return halfOption;
    }

    public void setHalfOption(String halfOption)
    {
        if(halfOption.equalsIgnoreCase("Full") ||
                halfOption.equalsIgnoreCase("F1/2") ||
                halfOption.equalsIgnoreCase("S1/2"))
        this.halfOption = halfOption;
    }

    public String getQualifier()
    {
        return qualifier;
    }

    public void setQualifier(String qualifier)
    {
        this.qualifier = qualifier;
    }

    public double getTotalCost(int sizeIndex)
    {
        double tempCost = cost[sizeIndex];
        if(!(halfOption.equalsIgnoreCase("Full")))
            tempCost = halfCost[sizeIndex];
        if(qualifier.equalsIgnoreCase("Extra"))
            tempCost *= 2;

        return tempCost;
    }
}
