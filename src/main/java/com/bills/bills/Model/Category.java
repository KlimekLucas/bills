package com.bills.bills.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_Id")
    @JsonProperty("categoryId")
    private Integer id;

    @NotEmpty(message = "pole nie może być puste")
    @Column(name = "NAME")
    @JsonProperty("NAME")
    private String name;


    @OneToMany
    private List<SubCategory> subCategories = new ArrayList<>();

    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public void addSubCategory(SubCategory subCategory) {
        subCategories.add(subCategory);
    }

    public void removeSubCategory(SubCategory subCategory) {
        subCategories.add(subCategory);
    }

}

