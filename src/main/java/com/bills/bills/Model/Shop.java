package com.bills.bills.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shop")
public class Shop implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_Id")
    @JsonProperty("shopId")
    private Integer id;

    @Column(name = "NAME")
    @JsonProperty("Name")
    private String name;

    @Column(name = "CITY")
    @JsonProperty("City")
    private String city;

    @Column(name = "Address")
    @JsonProperty("Address")
    private String address;


    public Shop() {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
