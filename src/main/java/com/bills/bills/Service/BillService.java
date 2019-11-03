package com.bills.bills.Service;

import com.bills.bills.Model.Bill;

import java.util.List;

public interface BillService {

    Bill getBill(Integer billId);

    List<Bill> getBills();

    void save(Bill bill);

    void delete(Bill bill);

}
