/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package ensolvers.challenge.backend.model.note;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author rauls
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NoteRegisterDTO{
    @NotBlank
    private String title; 
        
    @NotBlank
    private String content; 
        
    @NotBlank
    private String username;
        
    private Long category_id;
}
