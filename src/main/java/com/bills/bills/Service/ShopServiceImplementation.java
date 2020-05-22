package com.bills.bills.Service;

import com.bills.bills.Model.Shop;
import com.bills.bills.Repository.ShopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImplementation implements ShopService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private final ShopRepository shopRepository;

    @Autowired
    public ShopServiceImplementation(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public List<Shop> getShops() {
        log.info("getAllShops -> entering");
        return shopRepository.findAll();
    }

    @Override
    public Shop getShop(Integer shopId) {
        log.info("getShop -> entering");
        log.info("getCategory -> getting data from db ");
        Optional<Shop> shop = shopRepository.findById(shopId);
        return shop.orElse(defaultShop());
    }

    private Shop defaultShop() {
        log.info("defaultCategory -> entering");
        Shop defaultShop = new Shop();
        defaultShop.setId(0);
        defaultShop.setName("nie ma takiego sklepu");
        log.info("defaultShop -> defaultShop Object object has been created");
        return defaultShop;
    }

    @Override
    public void save(Shop shop) {
        log.info("saveShop -> entering");
        shopRepository.save(shop);
    }

    @Override
    public void delete(Shop shop) {
        log.info("deleteShop");
        shopRepository.delete(shop);
    }
}
