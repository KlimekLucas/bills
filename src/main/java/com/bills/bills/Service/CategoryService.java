package com.bills.bills.Service;

import com.bills.bills.Model.Category;

import java.util.List;


public interface CategoryService {

    Category getCategory(Integer categoryId);

    List<Category> getCategories();

    void save(Category category);

    void delete(Category category);
}
