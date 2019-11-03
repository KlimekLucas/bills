package com.bills.bills.Service;

import com.bills.bills.Model.Shop;

import java.util.List;

public interface ShopService {

    List<Shop> getShops();

    Shop getShop(Integer shopId);

    void save(Shop shop);

    void delete(Shop shop);
}
