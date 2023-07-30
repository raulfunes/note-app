/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensolvers.challenge.backend.model;

import ensolvers.challenge.backend.model.note.NoteRegisterDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 *
 * @author rauls
 */
@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime creation_date;
    private LocalDateTime update_date;
    private LocalDateTime expiration_date;
    
    @Enumerated(EnumType.STRING)
    private State state;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id")
    private Category category;

    public Note(NoteRegisterDTO note) {
        this.title = note.getTitle();
        this.content = note.getContent();
        this.creation_date = LocalDateTime.now();
        this.update_date = LocalDateTime.now();
        this.state = State.ACTIVE;
    } 
    
    public void archiveNote(){
        if (this.state.equals(State.ACTIVE)){
            this.state = State.ARCHIVED;
        } else {
            throw new IllegalArgumentException();
        }
    }
    public void dearchiveNote(){
        if (this.state.equals(State.ARCHIVED)){
            this.state = State.ACTIVE;
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    public void expireNote(){
        if (this.state.equals(State.ACTIVE)){
            this.state = State.EXPIRED;
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    public void deleteNote(){
        if (this.state.equals(State.ARCHIVED)){
            this.state = State.DELETED;
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    
}
