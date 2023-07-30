/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package ensolvers.challenge.backend.model.user;

import ensolvers.challenge.backend.model.Category;
import ensolvers.challenge.backend.model.Note;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;

/**
 *
 * @author rauls
 */
public record UserUpdateDTO(
        @NotBlank
        String name, 
        @NotBlank
        String username, 
        @NotBlank
        @Email
        String email) {
}
