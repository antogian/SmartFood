package com.athena.model;

public class SizeDTO
{
    private String name;
    private boolean selected = false;
    private double cost;

    public SizeDTO()
    {
    }

    public SizeDTO(String name, boolean selected, double cost)
    {
        this.name = name;
        this.selected = selected;
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

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
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
