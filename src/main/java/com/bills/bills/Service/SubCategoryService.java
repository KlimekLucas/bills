package com.bills.bills.Service;

import com.bills.bills.Model.SubCategory;

import java.util.List;

public interface SubCategoryService {

    SubCategory getOne(Integer subCategoryId);

    List<SubCategory> getAll();

    void save(SubCategory subCategory);


    void delete(SubCategory subCategory);
}
