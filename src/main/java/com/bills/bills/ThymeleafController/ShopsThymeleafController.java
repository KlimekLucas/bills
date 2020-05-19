package com.bills.bills.ThymeleafController;

import com.bills.bills.Model.Shop;
import com.bills.bills.Service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
