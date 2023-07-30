/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensolvers.challenge.backend.controller;

import ensolvers.challenge.backend.model.Category;
import ensolvers.challenge.backend.model.category.CategoryListDTO;
import ensolvers.challenge.backend.model.category.CategoryRegisterDTO;
import ensolvers.challenge.backend.model.category.CategoryUpdateDTO;
import ensolvers.challenge.backend.model.note.NoteListDTO;
import ensolvers.challenge.backend.model.note.NoteRegisterDTO;
import ensolvers.challenge.backend.model.note.NoteUpdateDTO;
import ensolvers.challenge.backend.service.CategoryService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author rauls
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping
    public Page<CategoryListDTO> findAllCategories(Pageable pageable, Authentication authentication){
        return categoryService.getCategoriesByUser(pageable, authentication.getName());
    }
    
    @PostMapping
    @PreAuthorize("authentication.getName().equals(#categoryDTO.username())")
    public ResponseEntity<CategoryListDTO> addCategory(@RequestBody @Valid CategoryRegisterDTO categoryDTO, UriComponentsBuilder uriComponentsBuilder){
        CategoryListDTO createdCategory = categoryService.saveCategory(categoryDTO);
        URI url = uriComponentsBuilder.path("/category/{id}").buildAndExpand(createdCategory.id()).toUri();
        return ResponseEntity.created(url).body(createdCategory);
    }
    
    @PutMapping
    public ResponseEntity<CategoryListDTO> updateNote(@RequestBody @Valid CategoryUpdateDTO categoryDTO, Authentication authentication){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDTO, authentication));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteNote(@PathVariable Long id, Authentication authentication){
        categoryService.deleteCategory(id, authentication);
        return ResponseEntity.noContent().build();
    }
}
