package com.example.demo.service;

import com.example.demo.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Locale;
import java.util.Optional;

public interface ICategoryService{
    Page<Category> findAll(Pageable pageable);
    Optional<Category> findById(Long id);
    void deleteById(Long id);
    Category save(Category category);
    boolean existsByNameCategory(String nameCategory);
}
