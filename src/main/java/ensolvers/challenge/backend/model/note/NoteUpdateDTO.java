/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package ensolvers.challenge.backend.model.note;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author rauls
 */
public record NoteUpdateDTO(
        @NotNull
        Long id,
        
        @NotBlank
        String title, 
        
        @NotBlank
        String content,
        
        Long category_id, 
        
        @Future
        LocalDateTime expire_date) {

}
