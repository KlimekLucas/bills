package com.bills.bills.Service;

import com.bills.bills.Model.Bill;
import com.bills.bills.Repository.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImplementation implements BillService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    BillRepository billRepository;

    @Autowired
    public BillServiceImplementation(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Bill getBill(Integer billId) {
        log.info("getBill -> entering");
        log.info("getBill -> getting data from db ");
        Optional<Bill> shop = billRepository.findById(billId);
        return shop.orElse(defaultBill());

    }

    private Bill defaultBill() {
        log.info("defaultBill -> entered");
        Bill defaultBill = new Bill();
        log.info("defaultBill -> defaultBill Object object has been created");
        return defaultBill;
    }

    @Override
    public List<Bill> getBills() {
        log.info("getBills -> entering");
        List<Bill> bills = billRepository.findAll();
        log.info("getBills -> exit");
        return bills;
    }

    @Override
    public void save(Bill bill) {
        log.info("saveBill -> entering");
        billRepository.save(bill);
    }

    @Override
    public void delete(Bill bill) {
        log.info("deleteBill");
        billRepository.delete(bill);
    }
}
