package com.bills.bills.ThymeleafController;

import com.bills.bills.Model.Category;
import com.bills.bills.Model.DTO.ItemDTO;
import com.bills.bills.Model.Item;
import com.bills.bills.Model.Shop;
import com.bills.bills.Service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/sho")
public class ShopsThymeleafController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private final ShopService shopService;

    public ShopsThymeleafController(ShopService shopService) {
        this.shopService = shopService;
    }


    @GetMapping("/all")
    public String ShowAllShops(Model model) {
        log.info("ShowAllShopsThymeleaf -> entered");
        List<Shop> shops = shopService.getShops();
        model.addAttribute("shopsList", shops);
        return "shopsList";
    }

    @GetMapping("/new")
    public String addShopForm(Model model) {
        model.addAttribute("addedShop", new Shop());
        return "addShop";
    }
    @PostMapping("/shops")
    public String saveShop(
            @Valid @ModelAttribute("addedShop") Shop newShop, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("addedShop", newShop);
            return "addShop";
        }
        shopService.save(newShop);
        return "redirect:/sho/all";
    }



    @RequestMapping("delete/{shopId}")
    public String DeleteShop(
            @PathVariable("shopId") Integer shopId,
            Model model) {
        log.info("DeleteShop -> entered");
        Shop shop = shopService.getShop(shopId);
        Integer shopID = shop.getId();
        if (shop != null) {
            log.info("DeleteShop -> shop Not exist");
            model.addAttribute("shop", shop);
            model.addAttribute("shopID", shopID);
        }
        return "deleteShop";
    }

    @RequestMapping("delete/{shopId}/deleted")
    public String DeleteShopFromDb(@PathVariable("shopId") Integer shopId, Model model) {
        Shop shop = shopService.getShop(shopId);
        model.addAttribute("shop", shop);
        log.info("DeleteItemFromDb -> deleting");
        shopService.delete(shop);
        return "redirect:/sho/all";
    }

    @RequestMapping("/edit/{shopId}")
    public String editCategory(@PathVariable("shopId") Integer shopId, Model model) {
        Shop shop = shopService.getShop(shopId);
        model.addAttribute("shopToEdit", shop);
        return "editShop";
    }
}
