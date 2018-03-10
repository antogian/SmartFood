package com.athena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication
{
    public static void main(String[] args)
    {
        /*List<FoodCollection> foodCollectionList = new ArrayList<FoodCollection>();

        for(int i=1;i<=10;i++){
            FoodCollection food = new FoodCollection(i,"food"+i,"food description"+i, 1.0*i);
            foodCollectionList.add(food);
        }

        for(FoodCollection food : foodCollectionList){
            System.out.println(food.getName()+", "+food.getDescription()+", "+food.getPrice()+", "+food.getFoodId());
        }*/

        SpringApplication.run(MainApplication.class, args);

    }
}
