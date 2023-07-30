/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ensolvers.challenge.backend.repository;

import ensolvers.challenge.backend.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rauls
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    Page<Category> findCategoryByUser_Username(Pageable pageable, String username);
}
