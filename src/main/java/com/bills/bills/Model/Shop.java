package com.bills.bills.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "shop")
public class Shop {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_Id")
    @JsonProperty("shopId")
    private Integer Id;

    @Column(name = "NAME")
    @JsonProperty("Name")
    private String name;

    @Column(name = "CITY")
    @JsonProperty("City")
    private String city;

    @Column(name = "Address")
    @JsonProperty("Address")
    private String Address;


    public Shop() {
    }


    public Integer getShopId() {
        return Id;
    }

    public void setShopId(Integer shopId) {
        this.Id = shopId;
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
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
