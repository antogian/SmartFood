package com.athena.model;

import com.athena.entities.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart
{
    private List<Product> selectedProducts;
    private Double totalPrice;

    public ShoppingCart()
    {
        this.selectedProducts = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addProduct(Product newProduct)
    {
        selectedProducts.add(newProduct);
        totalPrice += newProduct.getPrice();
    }

    public void removeProduct(String uuid)
    {
        for(int i = 0; i< selectedProducts.size(); i++)
        {
            if (selectedProducts.get(i).getId().equals(uuid))
            {
                totalPrice -= selectedProducts.get(i).getPrice();
                selectedProducts.remove(i);
                return;
            }
        }
    }

    public Product getProductById(String uuid)
    {
        for(int i=0; i<selectedProducts.size(); i++)
        {
            if(selectedProducts.get(i).getId().equals(uuid))
            {
                return selectedProducts.get(i);
            }
        }
        return new Product();
    }

    public List<Product> getSelectedProducts()
    {
        return selectedProducts;
    }

    public void setSelectedProducts(List<Product> selectedProducts)
    {
        this.selectedProducts = selectedProducts;
    }

    public Double getTotalPrice()
    {
        BigDecimal bd = new BigDecimal(Double.toString(totalPrice));
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }

    public void setTotalPrice(Double totalPrice)
    {
        this.totalPrice = totalPrice;
    }
}
