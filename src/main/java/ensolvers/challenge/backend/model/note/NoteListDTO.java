/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package ensolvers.challenge.backend.model.note;

import ensolvers.challenge.backend.model.Note;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author rauls
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoteListDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime creation_date;
    private LocalDateTime update_date;
    private LocalDateTime expiration_date;
    private String category;
    private String state;
    private String username;
    private Long category_id;
    public NoteListDTO(Note note) {
        this.id = note.getId();
        this.title = note.getTitle();
        this.content = note.getContent();
        this.creation_date = note.getCreation_date();
        this.update_date = note.getUpdate_date();
        this.expiration_date = note.getExpiration_date();
        this.state = note.getState().toString();
        this.username = note.getUser().getUsername();
        this.category = note.getCategory() == null? "Sin categoria" : note.getCategory().getName();
        this.category_id = note.getCategory() == null? null : note.getCategory().getId();
    }
}
