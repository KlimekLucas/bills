package com.bills.bills.ThymeleafController;


import com.bills.bills.Exceptions.NotFoundException;
import com.bills.bills.Model.Category;
import com.bills.bills.Model.DTO.ItemDTO;
import com.bills.bills.Model.Item;
import com.bills.bills.Service.CategoryService;
import com.bills.bills.Service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/ite")
public class ItemsThymeleafController {

    private final ItemService itemService;
    private final CategoryService categoryService;
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ItemsThymeleafController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }


    @GetMapping("/all")
    public String ShowAllItems(Model model) {
        log.info("ShowAllItemsThymeleaf -> entered");
        List<Item> items = itemService.getItems();
        model.addAttribute("itemsList", items);
        return "itemsList";
    }


    @GetMapping("/new")
    public String addItem(Model model) {
        List<Category> availableCategories = categoryService.getCategories();
        model.addAttribute("availableCategories", availableCategories);

        if (!model.containsAttribute("addedWorker")) {
            model.addAttribute("addedItem", new ItemDTO());
            model.addAttribute("availableCategories", availableCategories);
        }
        return "addItem";
    }

    @RequestMapping("delete/{itemId}")
    public String DeleteItem(
            @PathVariable("itemId") Integer itemId,
            Model model) {
        log.info("DeleteItem -> entered");
        Item item = itemService.getItem(itemId);
        Integer itemID = item.getId();
        if (item != null) {
            log.info("DeleteItem -> item Not exist");
            model.addAttribute("item", item);
            model.addAttribute("itemID", itemID);
        }
        return "deleteItem";
    }

    @RequestMapping("delete/{itemId}/deleted")
    public String DeleteItemFromDb(@PathVariable("itemId") Integer itemId, Model model) {
        Item item = itemService.getItem(itemId);
        model.addAttribute("item", item);
        log.info("DeleteItemFromDb -> deleting");
        itemService.delete(item);
        return "redirect:/ite/all";
    }


    @PostMapping("/items")
    public String saveItem(@Valid @ModelAttribute("addedItem") ItemDTO addedItem, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("addedItem", addedItem);
            List<Category> availableCategories = categoryService.getCategories();
            modelMap.addAttribute("availableCategories", availableCategories);
            return "addItem";
        }
        try {
            itemService.save(addedItem);
        } catch (NotFoundException e) {
            e.getMessage();
            redirectAttributes.addFlashAttribute("addedItem", addedItem);
            redirectAttributes.addFlashAttribute("error", "nie istnieje");
            return "redirect:/ite/new";
        }
        return "addItem";
    }
}
