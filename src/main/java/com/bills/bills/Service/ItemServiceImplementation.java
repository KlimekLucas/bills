package com.bills.bills.Service;

import com.bills.bills.Exceptions.NotFoundException;
import com.bills.bills.Model.Category;
import com.bills.bills.Model.DTO.ItemDTO;
import com.bills.bills.Model.Item;
import com.bills.bills.Repository.CategoryRepository;
import com.bills.bills.Repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImplementation implements ItemService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    ItemRepository itemRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public ItemServiceImplementation(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Item> getItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }

    @Override
    public Item getItem(Integer itemId) {
        log.info("getItem -> entering");
        log.info("getItem -> getting data from db ");
        Optional<Item> item = itemRepository.findById(itemId);
        return item.orElse(defaultItem());
    }

    private Item defaultItem() {
        log.info("creating default item");
        Item defaultItem = new Item();
        defaultItem.setName("taki artykuł nie istnieje");
        return defaultItem;
    }

    @Override
    public void save(ItemDTO itemDTO) {
        log.info("saveItem -> enter");
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setBrand(itemDTO.getBrand());
        item.setInfo(itemDTO.getInfo());
        item.setPrice(itemDTO.getPrice());
        log.info("saveItem -> getCategoryfromRepository");
        Optional<Category> category = categoryRepository.findById(Integer.valueOf(itemDTO.getCategory()));
        item.setCategory(category.orElseThrow(() -> new NotFoundException("nie ma takiej roli")));
        itemRepository.save(item);
        log.info("saveItem -> item Saved");
    }

    @Override
    public void delete(Item item) {
        log.info("deleteItem -> enter");
        itemRepository.delete(item);
    }

}
