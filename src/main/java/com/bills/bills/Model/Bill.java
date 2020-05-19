package com.bills.bills.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bill")
public class Bill implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_Id")
    @JsonProperty("billId")
    private Integer id;

    @Column(name = "DATE")
    @JsonProperty("DATE")
    private Date date;

    @ManyToOne
    @JsonProperty("SHOP")
    private Shop shop;

    @OneToMany
    @JsonProperty("ITEMS_LIST")
    private List<Item> item;

    public Bill() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }
}
