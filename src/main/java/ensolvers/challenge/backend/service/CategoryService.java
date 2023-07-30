/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensolvers.challenge.backend.service;

import ensolvers.challenge.backend.model.Category;
import ensolvers.challenge.backend.model.User;
import ensolvers.challenge.backend.model.category.CategoryListDTO;
import ensolvers.challenge.backend.model.category.CategoryRegisterDTO;
import ensolvers.challenge.backend.model.category.CategoryUpdateDTO;
import ensolvers.challenge.backend.repository.CategoryRepository;
import ensolvers.challenge.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 *
 * @author rauls
 */
@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    
    public CategoryListDTO saveCategory(CategoryRegisterDTO categoryDTO){
        User returnedUser = userRepository.findByUsername(categoryDTO.username())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + categoryDTO.username()));
        Category category = new Category(categoryDTO);
        category.setUser(returnedUser);
        return new CategoryListDTO(categoryRepository.save(category)) ;
    }
    
    public Page<CategoryListDTO> getCategoriesByUser(Pageable pageable, String username){
        return categoryRepository.findCategoryByUser_Username(pageable, username).map(CategoryListDTO::new);
    }
    
    public CategoryListDTO updateCategory(CategoryUpdateDTO category, Authentication authentication){
        Category returnedCategory = categoryRepository.findById(category.id())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + category.id()));
        if (!returnedCategory.getUser().getUsername().equals(authentication.getName())) {
            throw new IllegalArgumentException();
        }
        
        returnedCategory.setName(category.name());
        
        return new CategoryListDTO(categoryRepository.save(returnedCategory));
    }
    
    public void deleteCategory(Long id, Authentication authentication){
        Category returnedCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + id));
        if (!returnedCategory.getUser().getUsername().equals(authentication.getName())) {
            throw new IllegalArgumentException();
        }
        categoryRepository.deleteById(id);
    }
}
