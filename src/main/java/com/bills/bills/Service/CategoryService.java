package com.bills.bills.Service;

import com.bills.bills.Model.Category;

import java.util.List;


public interface CategoryService {

    Category getCategory(Integer categoryId);

    Category findByName(String name);

    List<Category> getCategories();

    void save(Category category);

    void delete(Category category);

}
