package com.athena.entities;

//import javax.persistence.*;
import java.io.Serializable;

//@Entity
//@Table(name="food_collection")
//@EnableAutoConfiguration(exclude=DataSourceAutoConfiguration.class)
public class Product implements Serializable {


//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String productId;

//    @Column(name = "name")
    private String name;

//    @Column(name = "description")
    private String description;

//    @Column(name = "price")
    private Double price;

    public Product()
    {
        this.productId = "";
        this.name = "";
        this.description = "";
        this.price = 0.0;
    }

    public Product(String productId, String name, String description, Double price)
    {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getProductId() {
        return productId;
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
