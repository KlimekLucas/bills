package com.bills.bills.Service;

import com.bills.bills.Model.Category;
import com.bills.bills.Repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category getCategory(Integer categoryId) {
        log.info("getCategory -> entering ");
        log.info("getCategory -> getting data from db ");
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.orElse(defaultCategory());

    }

    @Override
    public Category findByName(String name) {
        Category category = categoryRepository.findByName(name);
        return category;
    }


    @Override
    public List<Category> getCategories() {
        log.info("getCategories -> entering");
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }


    public void save(Category NewCategory) {
        String NewCategoryName = NewCategory.getName();
        log.info("save NewCategory -> entering");
        boolean exists = false;
        List<Category> categories = getCategories();
        for (Category category : categories) {
            if (category.getName().equals(NewCategoryName)) {
                exists = true;
                log.error("save NewCategory -> this category already exist in database");
            }
        }
        if (exists == false) {
            categoryRepository.save(NewCategory);
            log.info("save NewCategory -> saving");
        }
    }

    @Override
    public void delete(Category category) {
        log.info("deleting");
        categoryRepository.delete(category);
    }


    private Category defaultCategory() {
        log.info("defaultCategory -> entering");
        Category defaultCategory = new Category();
        defaultCategory.setId(0);
        defaultCategory.setName("nie ma takiej kategori");
        log.info("defaultCategory -> DefaultCategory Object object has been created");
        return defaultCategory;
    }
}
