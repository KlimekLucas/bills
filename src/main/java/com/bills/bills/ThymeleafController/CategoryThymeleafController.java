package com.bills.bills.ThymeleafController;


import com.bills.bills.Model.Category;
import com.bills.bills.Service.CategoryService;
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
@RequestMapping("/cat")
public class CategoryThymeleafController {

    private final CategoryService categoryService;
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CategoryThymeleafController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/new")
    public String addClientForm(Model model) {
        model.addAttribute("addedCategory", new Category());
        return "addCategory";
    }

    @PostMapping("/categories")
    public String saveCategory(
            @Valid @ModelAttribute("addedCategory") Category newCategory, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("addedClient", newCategory);
            return "addCategory";
        }
        categoryService.save(newCategory);
        return "redirect:/cat/all";
    }

    @GetMapping("/categories/{categoryName}")
    public Category findByTitle(@PathVariable String categoryName) {
        return categoryService.findByName(categoryName);
    }

    @GetMapping("/{id}")
    public Category findOne(@PathVariable Integer id) {
        return categoryService.getCategory(id);
    }

    @RequestMapping("delete/{categoryId}")
    public String DeleteCategory(
            @PathVariable("categoryId") Integer categoryId,
            Model model) {
        log.info("DeleteCategory -> entered");
        Category category = categoryService.getCategory(categoryId);
        Integer categoryID = category.getId();
        if (category != null) {
            log.info("DeleteCategory -> category Not exist");
            model.addAttribute("category", category);
            model.addAttribute("categoryID", categoryID);
        }
        return "deleteCategory";
    }

    @GetMapping("/all")
    public String ShowAllCategories(Model model) {
        log.info("ShowAllCategoriesThymeleaf -> entered");
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categoriesList", categories);
        return "categoriesList";
    }

    @RequestMapping("delete/{categoryId}/deleted")
    public String DeleteCategoryFromDb(@PathVariable("categoryId") Integer categoryId, Model model) {
        Category category = categoryService.getCategory(categoryId);
        model.addAttribute("category", category);
        log.info("DeleteCategoryFromDb -> deleting");
        categoryService.delete(category);
        return "redirect:/cat/all";
    }

    @RequestMapping("edit/{categoryId}")
    public String EditCategory(
            @PathVariable("categoryId") Integer categoryId,
            Model model) {
        log.info("EditCategory -> entered");
        Category category = categoryService.getCategory(categoryId);
        Integer categoryID = category.getId();
        if (category != null) {
            log.info("EditCategory -> category Not exist");
            model.addAttribute("category", category);
            model.addAttribute("categoryID", categoryID);
        }
        return "editCategory";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }
}
