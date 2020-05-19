package com.bills.bills.Controller;

import com.bills.bills.Model.Shop;
import com.bills.bills.Service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("shops")
public class ShopController {


    Logger log = LoggerFactory.getLogger(this.getClass());
    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }


    @GetMapping(value = "{shopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Shop getOneShop(@PathVariable("shopId") Integer shopId, Model model) {
        log.info("getOneShop in ShopController -> entered");
        Shop shop = shopService.getShop(shopId);
        log.info("getOneShop in ShopController -> exiting");
        return shop;
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Shop> getAllShops(Model model) {
        log.info("entered");
        List<Shop> shops = shopService.getShops();
        return shops;
    }

    @PostMapping(value = "new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void AddNewShop(@RequestBody Shop shop) {
        log.info("entered");
        shopService.save(shop);
    }

    @RequestMapping("delete/{shopId}")
    public void DeleteCategory(@PathVariable("shopId") Integer shopId, Model model) {
        log.info("DeleteShop");
        Shop shop = shopService.getShop(shopId);
        shopService.delete(shop);
    }
}
