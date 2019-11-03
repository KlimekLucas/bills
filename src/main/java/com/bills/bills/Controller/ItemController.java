package com.bills.bills.Controller;

import com.bills.bills.Model.Item;
import com.bills.bills.Service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping(value = "{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Item getOneItem(@PathVariable("itemId") Integer itemId, Model model) {
        log.info("getOneItem -> entered");
        Item item = itemService.getItem(itemId);
        return item;
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Item> getAllCategories(Model model) {
        log.info("getAllCategories -> entered");
        List<Item> items = itemService.getItems();
        log.info("getAllCategories -> exiting");
        return items;
    }

    @PostMapping(value = "new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void AddNewItem(@RequestBody Item item) {
        log.info("AddNewItem -> entered");
        itemService.save(item);
        log.info("AddNewItem -> exiting");
    }

    @RequestMapping("delete/{ItemId}")
    public void DeleteCategory(@PathVariable("ItemId") Integer ItemId, Model model) {
        log.info("DeleteItem");
        Item item = itemService.getItem(ItemId);
        itemService.delete(item);
    }
}
