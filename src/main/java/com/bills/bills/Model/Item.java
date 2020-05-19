package com.bills.bills.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "item")
public class Item implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_Id")
    @JsonProperty("itemId")
    private Integer id;

    @ManyToOne
    @JsonProperty("category")
    @JoinColumn(name = "categoryId")
    private Category category;

    @Column
    @JsonProperty("brand")
    private String brand;

    @NotEmpty(message = "pole nie może być puste")
    @Column(name = "NAME")
    @JsonProperty("NAME")
    private String name;


    @Column(name = "PRICE")
    @JsonProperty("PRICE")
    private Double price;

    @Column
    @JsonProperty("info")
    private String info;

    public Item() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}