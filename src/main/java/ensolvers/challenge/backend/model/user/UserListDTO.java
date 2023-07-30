/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package ensolvers.challenge.backend.model.user;

import ensolvers.challenge.backend.model.Category;
import ensolvers.challenge.backend.model.Note;
import ensolvers.challenge.backend.model.User;
import ensolvers.challenge.backend.model.category.CategoryListDTO;
import ensolvers.challenge.backend.model.note.NoteListDTO;
import java.util.List;
import java.util.Set;

/**
 *
 * @author rauls
 */
public record UserListDTO(String name, String username, String email, List<NoteListDTO> notes, List<CategoryListDTO> categories) {

    public UserListDTO(User user) {
        this(user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getNotes().stream().map(NoteListDTO::new).toList(),
                user.getCategories().stream().map(CategoryListDTO::new).toList());
    }
}
