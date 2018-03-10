package com.athena.entities;


//import javax.persistence.*;
import java.io.Serializable;

//@Entity
//@Table(name="food_collection")
//@EnableAutoConfiguration(exclude=DataSourceAutoConfiguration.class)
public class FoodCollection implements Serializable {


//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String foodId;

//    @Column(name = "name")
    private String name;

//    @Column(name = "description")
    private String description;

//    @Column(name = "price")
    private Double price;

    public FoodCollection(String foodId, String name, String description, Double price) {
        this.foodId = foodId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getFoodId() {
        return foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
