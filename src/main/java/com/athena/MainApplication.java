package com.athena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication
{
    public static void main(String[] args)
    {
        /*List<Product> foodCollectionList = new ArrayList<Product>();

        for(int i=1;i<=10;i++){
            Product food = new Product(i,"food"+i,"food description"+i, 1.0*i);
            foodCollectionList.add(food);
        }

        for(Product food : foodCollectionList){
            System.out.println(food.getName()+", "+food.getDescription()+", "+food.getPrice()+", "+food.getId());
        }*/

        SpringApplication.run(MainApplication.class, args);

    }
}
