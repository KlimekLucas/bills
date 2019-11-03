package com.bills.bills.Service;

import com.bills.bills.Model.SubCategory;
import com.bills.bills.Repository.SubCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryServiceImplementation implements SubCategoryService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    public SubCategoryServiceImplementation(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public SubCategory getOne(Integer subCategoryId) {

        log.info("getCategory -> entering ");
        log.info("getCategory -> getting data from db ");
        Optional<SubCategory> category = subCategoryRepository.findById(subCategoryId);
        return category.orElse(defaultSubCategory());
    }

    private SubCategory defaultSubCategory() {
        log.info("defaultSubCategory -> entering ");
        SubCategory DefaultSubCategory = new SubCategory();
        defaultSubCategory().setId(0);
        log.info("defaultCategory -> DefaultCategory Object object has been created");
        return defaultSubCategory();
    }


    @Override
    public List<SubCategory> getAll() {
        log.info("getAllSubCategory");
        return subCategoryRepository.findAll();
    }

    @Override
    public void save(SubCategory subCategory) {
        log.info("saveSubCategory");
        subCategoryRepository.save(subCategory);
    }

    @Override
    public void delete(SubCategory subCategory) {
        log.info("deleteSubCategory");
        subCategoryRepository.delete(subCategory);
    }
}
