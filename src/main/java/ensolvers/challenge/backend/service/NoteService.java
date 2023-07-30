/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensolvers.challenge.backend.service;

import ensolvers.challenge.backend.model.Category;
import ensolvers.challenge.backend.model.Note;
import ensolvers.challenge.backend.model.User;
import ensolvers.challenge.backend.model.note.NoteListDTO;
import ensolvers.challenge.backend.model.note.NoteRegisterDTO;
import ensolvers.challenge.backend.model.note.NoteUpdateDTO;
import ensolvers.challenge.backend.repository.CategoryRepository;
import ensolvers.challenge.backend.repository.NoteRepository;
import ensolvers.challenge.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author rauls
 */
@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Page<NoteListDTO> getNotes(Pageable pageable) {
        return noteRepository.findAll(pageable).map(NoteListDTO::new);
    }

    public NoteListDTO getNoteById(Long id) {
        Note returnedNote = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with ID: " + id));
        return new NoteListDTO(returnedNote);
    }

    public Page<NoteListDTO> getNotesByUsername(Pageable pageable, String username) {
        return noteRepository.findNoteByUser_Username(pageable, username).map(NoteListDTO::new);
    }

    public NoteListDTO saveNote(NoteRegisterDTO noteDTO, Authentication authentication) {
        User returnedUser = userRepository.findByUsername(noteDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + noteDTO.getUsername()));
        Note note = new Note(noteDTO);
        note.setUser(returnedUser);
        System.out.println("\n\n\n" + noteDTO.getCategory_id());
        if (noteDTO.getCategory_id() != null) {
            Category returnedCategory = categoryRepository.findById(noteDTO.getCategory_id())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + noteDTO.getCategory_id()));
            if (!returnedCategory.getUser().getUsername().equals(authentication.getName())) {
                throw new IllegalArgumentException("Category doesnt exist");
            }
            note.setCategory(returnedCategory);
        }

        return new NoteListDTO(noteRepository.save(note));
    }

    public NoteListDTO updateNote(NoteUpdateDTO noteDTO, Authentication authentication) {
        Note returnedNote = noteRepository.findById(noteDTO.id())
                .orElseThrow(() -> new IllegalArgumentException("Note not found with ID: " + noteDTO.id()));
        if (!returnedNote.getUser().getUsername().equals(authentication.getName())) {
            throw new IllegalArgumentException();
        }
        returnedNote.setTitle(noteDTO.title());
        returnedNote.setContent(noteDTO.content());
        returnedNote.setCategory(null);
        if (noteDTO.category_id() != null) {
            Category returnedCategory = categoryRepository.findById(noteDTO.category_id())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + noteDTO.category_id()));
            if (!returnedCategory.getUser().getUsername().equals(authentication.getName())) {
                throw new IllegalArgumentException("Category doesnt exist");
            }
            returnedNote.setCategory(returnedCategory);
        }
        
        if (noteDTO.expire_date() != null) {
            returnedNote.setExpiration_date(noteDTO.expire_date());
        }
        returnedNote.setUpdate_date(LocalDateTime.now());

        return new NoteListDTO(noteRepository.save(returnedNote));
    }

    @Transactional
    public void deleteNote(@PathVariable Long id, Authentication authenticarion) {
        Note returnedNote = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with ID: " + id));
        if (!returnedNote.getUser().getUsername().equals(authenticarion.getName())) {
            throw new IllegalArgumentException();
        }
        returnedNote.deleteNote();
    }

    @Transactional
    public void archiveNote(@PathVariable Long id, Authentication authenticarion) {
        Note returnedNote = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with ID: " + id));
        if (!returnedNote.getUser().getUsername().equals(authenticarion.getName())) {
            throw new IllegalArgumentException();
        }
        returnedNote.archiveNote();
    }

    @Transactional
    public void dearchiveNote(@PathVariable Long id, Authentication authenticarion) {
        Note returnedNote = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with ID: " + id));
        if (!returnedNote.getUser().getUsername().equals(authenticarion.getName())) {
            throw new IllegalArgumentException();
        }
        returnedNote.dearchiveNote();
    }

    @Transactional
    public void expireNote(@PathVariable Long id) {
        Note returnedNote = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with ID: " + id));
        returnedNote.expireNote();
    }
}
