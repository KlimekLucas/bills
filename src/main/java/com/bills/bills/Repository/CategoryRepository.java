package com.bills.bills.Repository;

import com.bills.bills.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Override
    Optional<Category> findById(Integer integer);

    @Override
    List<Category> findAll();

}
