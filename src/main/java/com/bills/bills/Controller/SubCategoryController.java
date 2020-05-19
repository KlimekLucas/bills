package com.bills.bills.Controller;


import com.bills.bills.Model.SubCategory;
import com.bills.bills.Service.SubCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subCategories")
public class SubCategoryController {


    Logger log = LoggerFactory.getLogger(this.getClass());
    private final SubCategoryService subCategoryService;

    @Autowired
    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping(value = "{subcategoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SubCategory getOneSubCategory(@PathVariable("subcategoryId") Integer subcategoryId, Model model) {
        log.info("getOneCategory -> entered");
        SubCategory subCategory = subCategoryService.getOne(subcategoryId);
        log.info("getOneCategory exiting");
        return subCategory;
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SubCategory> getAllSUbCategories(Model model) {
        log.info("getAllSUbCategories -> entered\"");
        List<SubCategory> subCategories = subCategoryService.getAll();
        log.info("getAllSUbCategories -> entered\"");
        return subCategories;
    }

    @PostMapping(value = "new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void AddNewSubCategory(@RequestBody SubCategory subCategory) {
        log.info("AddNewSubCategory -> entered");
        subCategoryService.save(subCategory);
        log.info("AddNewSubCategory -> exiting");
    }

    @RequestMapping("delete/{SubCategoryId}")
    public void DeleteCategory(@PathVariable("SubCategoryId") Integer subCategoryId, Model model) {
        log.info("DeleteSubCategory");
        SubCategory subCategory = subCategoryService.getOne(subCategoryId);
        subCategoryService.delete(subCategory);
    }

}
