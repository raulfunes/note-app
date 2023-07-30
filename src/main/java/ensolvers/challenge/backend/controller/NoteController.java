/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensolvers.challenge.backend.controller;

import ensolvers.challenge.backend.model.note.NoteListDTO;
import ensolvers.challenge.backend.model.note.NoteRegisterDTO;
import ensolvers.challenge.backend.model.note.NoteUpdateDTO;
import ensolvers.challenge.backend.service.NoteService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author rauls
 */
@RestController
@RequestMapping("/notes")
public class NoteController {
    
    @Autowired
    private NoteService noteService;
    
    @GetMapping("/{id}")
    public ResponseEntity<NoteListDTO> findNoteById(@PathVariable Long id){
        return ResponseEntity.ok(noteService.getNoteById(id));
    }
    
    @GetMapping("/user")
    public ResponseEntity<Page<NoteListDTO>> findNoteByUsername(Pageable pageable, Authentication authentication){
        return ResponseEntity.ok(noteService.getNotesByUsername(pageable, authentication.getName()));
    }
    
    @PostMapping
    @PreAuthorize("authentication.getName().equals(#noteDTO.getUsername())")
    public ResponseEntity<NoteListDTO> addNote(@RequestBody @Valid NoteRegisterDTO noteDTO, Authentication authentication, UriComponentsBuilder uriComponentsBuilder){
        NoteListDTO createdNote = noteService.saveNote(noteDTO, authentication);
        URI url = uriComponentsBuilder.path("/notes/{id}").buildAndExpand(createdNote.getId()).toUri();
        return ResponseEntity.created(url).body(createdNote);
    }
    
    @PutMapping
    public ResponseEntity<NoteListDTO> updateNote(@RequestBody @Valid NoteUpdateDTO noteDTO, Authentication authentication){
        return ResponseEntity.ok(noteService.updateNote(noteDTO, authentication));
    }
    
    
    @PatchMapping("/archive/{id}")
    public ResponseEntity archiveNote(@PathVariable Long id, Authentication authentication){
        noteService.archiveNote(id, authentication);
        return ResponseEntity.ok().build();
    }
 
    @PatchMapping("/dearchive/{id}")
    public ResponseEntity dearchiveNote(@PathVariable Long id, Authentication authentication){
        noteService.dearchiveNote(id, authentication);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteNote(@PathVariable Long id, Authentication authentication){
        noteService.deleteNote(id, authentication);
        return ResponseEntity.ok().build();
    }
}
