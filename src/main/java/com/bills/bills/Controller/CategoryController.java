package com.bills.bills.Controller;

import com.bills.bills.Model.Category;
import com.bills.bills.Service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping(value = "{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Category getOneCategory(@PathVariable("categoryId") Integer categoryId, Model model) {
        log.info("getOneCategory -> entered");
        Category category = categoryService.getCategory(categoryId);
        log.info("getOneCategory -> exiting");
        return category;
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getAllCategories(Model model) {
        log.info("getAllCategories -> entered");
        List<Category> categories = categoryService.getCategories();
        return categories;
    }

    @PostMapping(value = "new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void AddNewCategory(@RequestBody Category category) {
        log.info("AddNewCategory -> entered");
        categoryService.save(category);
    }

    @RequestMapping("delete/{categoryId}")
    public void DeleteCategory(@PathVariable("categoryId") Integer categoryId, Model model) {
        log.info("DeleteCategory");
        Category category = categoryService.getCategory(categoryId);
        categoryService.delete(category);
    }

// TODO
//    @PutMapping(value = "/{categoryId}",
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Category update(@RequestBody Category category, @PathVariable("categoryId") Integer categoryId) {
//        Category foundCategory = categoryService.getCategory(category.getCategoryId());
//        category.getCategoryId() = foundCategory.getCategoryId();
//        category.getCategory() = foundCategory.getCategory();
//        return categoryService.save(category);
//    }

}
