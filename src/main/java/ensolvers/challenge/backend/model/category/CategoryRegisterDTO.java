/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package ensolvers.challenge.backend.model.category;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author rauls
 */
public record CategoryRegisterDTO(
        @NotBlank
        String name,
        
        @NotBlank
        String username
        ) {

}
