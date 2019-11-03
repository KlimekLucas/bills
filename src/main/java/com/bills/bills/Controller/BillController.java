package com.bills.bills.Controller;

import com.bills.bills.Model.Bill;
import com.bills.bills.Service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bills")
public class BillController {


    Logger log = LoggerFactory.getLogger(this.getClass());
    private com.bills.bills.Service.BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }


    @GetMapping(value = "{BillId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Bill getOneBill(@PathVariable("BillId") Integer billId, Model model) {
        log.info("getOneBill -> entered");
        Bill Bill = billService.getBill(billId);
        log.info("getOneBill -> exiting");
        return Bill;
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bill> getAllBills(Model model) {
        log.info("getAllCategories -> entered");
        List<Bill> bills = billService.getBills();
        return bills;
    }

    @PostMapping(value = "new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void AddNewBill(@RequestBody Bill bill) {
        log.info("AddNewBill -> entered");
        billService.save(bill);
    }

    @RequestMapping("delete/{BillId}")
    public void DeleteBill(@PathVariable("BillId") Integer BillId, Model model) {
        log.info("DeleteBill");
        Bill bill = billService.getBill(BillId);
        billService.delete(bill);
    }
}
