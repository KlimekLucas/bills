package com.bills.bills.ThymeleafController;

import com.bills.bills.Model.Bill;
import com.bills.bills.Service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/bil")
public class BillsThymeleafController {

    private final BillService billService;
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public BillsThymeleafController(BillService billService) {
        this.billService = billService;
    }


    @GetMapping("/new")
    public String addClientForm(Model model) {
        model.addAttribute("addedBill", new Bill());
        return "addBill";
    }

    @PostMapping("/bil")
    public String saveBill(
            @Valid @ModelAttribute("addedBill") Bill newBill, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("addedBill", newBill);
            return "addBill";
        }
        billService.save(newBill);
        return "redirect:/bil/all";
    }


    @RequestMapping("delete/{BillId}")
    public String DeleteBill(
            @PathVariable("BillId") Integer BillId,
            Model model) {
        log.info("DeleteBill -> entered");
        Bill Bill = billService.getBill(BillId);
        Integer BillID = Bill.getId();
        if (Bill != null) {
            log.info("DeleteBill -> Bill Not exist");
            model.addAttribute("Bill", Bill);
            model.addAttribute("BillID", BillID);
        }
        return "deleteBill";
    }

    @GetMapping("/all")
    public String ShowAllBills(Model model) {
        log.info("ShowAllBillsThymeleaf -> entered");
        List<Bill> bills = billService.getBills();
        model.addAttribute("billsList", bills);
        return "billsList";
    }

    @RequestMapping("delete/{BillId}/deleted")
    public String DeleteBillFromDb(@PathVariable("BillId") Integer BillId, Model model) {
        Bill Bill = billService.getBill(BillId);
        model.addAttribute("Bill", Bill);
        log.info("DeleteBillFromDb -> deleting");
        billService.delete(Bill);
        return "redirect:/cat/all";
    }

    @RequestMapping("edit/{BillId}")
    public String EditBill(
            @PathVariable("BillId") Integer BillId,
            Model model) {
        log.info("EditBill -> entered");
        Bill Bill = billService.getBill(BillId);
        Integer BillID = Bill.getId();
        if (Bill != null) {
            log.info("EditBill -> Bill Not exist");
            model.addAttribute("Bill", Bill);
            model.addAttribute("BillID", BillID);
        }
        return "editBill";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }
}

