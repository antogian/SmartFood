package com.athena.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class ShoppingCart
{
    private List<CartEntry> entries;
    private double totalCost;

    public ShoppingCart()
    {
        entries = new ArrayList<>();
    }

    public List<CartEntry> getEntries()
    {
        return entries;
    }

    public void setEntries(List<CartEntry> entries)
    {
        this.entries = entries;
    }

    public double getTotalCost()
    {
        BigDecimal bd = new BigDecimal(Double.toString(totalCost));
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        //return bd.doubleValue();

        return bd.doubleValue();
    }

    public void setTotalCost(double totalCost)
    {
        this.totalCost = totalCost;
    }

    private void calculateCost()
    {
        totalCost = 0.0;
        for(CartEntry entry : entries)
        {
            totalCost += entry.getQuantity() * entry.getItem().getTotalCost();
        }
    }

    public void addEntry(CartEntry newEntry)
    {
        entries.add(newEntry);
        calculateCost();
    }

    public void removeEntryById(String id)
    {
        for(int i=0; i<entries.size(); i++)
        {
            if(entries.get(i).getItem().getId().equalsIgnoreCase(id))
            {
                entries.remove(i);
            }
        }
        calculateCost();
    }


}
