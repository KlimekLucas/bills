package com.bills.bills.Service;

import com.bills.bills.Model.DTO.ItemDTO;
import com.bills.bills.Model.Item;

import java.util.List;

public interface ItemService {

    List<Item> getItems();

    Item getItem(Integer itemId);

    void save(ItemDTO ItemDTO);

    void delete(Item item);
}
