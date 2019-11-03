package com.bills.bills.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    @ManyToOne
    @JsonProperty("category")
    Category category;
    @ManyToOne
    @JsonProperty("subCategory")
    SubCategory subCategory;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_Id")
    @JsonProperty("itemId")
    private Integer id;
    @Column(name = "NAME")
    @JsonProperty("NAME")
    private String name;
    @Column(name = "PRICE")
    @JsonProperty("PRICE")
    private Double price;


    public Item() {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
