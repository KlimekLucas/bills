package com.bills.bills.Service;

import com.bills.bills.Model.Item;

import java.util.List;

public interface ItemService {

    List<Item> getItems();

    Item getItem(Integer itemId);

    void save(Item item);

    void delete(Item item);
}
