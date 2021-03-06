package com.example.demo.controller;

import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.impl.CategoryServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("category")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    CategoryServiceimpl categoryService;
    @Autowired
    UserDetailService userDetailService;
    @GetMapping
    public ResponseEntity<?> pageCategory(@PageableDefault(sort = "nameCategory", direction = Sort.Direction.ASC)Pageable pageable){
        Page<Category> categoryPage = categoryService.findAll(pageable);
        if(categoryPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        User user = userDetailService.getCurrentUser();
        if(!user.getUsername().equals("Anonymous")){
            if(categoryService.existsByNameCategory(category.getNameCategory())){
                return new ResponseEntity<>(new ResponMessage("no_name_category"), HttpStatus.OK);
            }
            if(category.getAvatarCategory()==null){
                return new ResponseEntity<>(new ResponMessage("no_avatar_category"),HttpStatus.OK);
            }
            categoryService.save(category);
            return new ResponseEntity<>(new ResponMessage("create_success"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponMessage("create_failed"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.deleteById(category.get().getId());
        return new ResponseEntity<>(new ResponMessage("delete_success"), HttpStatus.OK);
    }
}
