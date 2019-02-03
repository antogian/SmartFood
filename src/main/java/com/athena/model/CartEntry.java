package com.athena.model;

import com.athena.entities.Item;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CartEntry
{
    private int quantity = 1;
    private ItemDTO item;

    public CartEntry()
    {
        item = new ItemDTO();
        quantity = 1;
    }

    public ItemDTO getItem()
    {
        return item;
    }

    public void setItem(ItemDTO item)
    {
        this.item = item;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

}
