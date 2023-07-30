/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package ensolvers.challenge.backend.model.category;

import ensolvers.challenge.backend.model.Category;
import ensolvers.challenge.backend.model.Note;
import ensolvers.challenge.backend.model.note.NoteListDTO;
import java.util.List;

/**
 *
 * @author rauls
 */
public record CategoryListDTO(Long id, String name, List<NoteListDTO> notes) {
    public CategoryListDTO(Category category){
        this(category.getId(), category.getName(), category.getNotes().stream().map(NoteListDTO::new).toList());
    }
      
}
