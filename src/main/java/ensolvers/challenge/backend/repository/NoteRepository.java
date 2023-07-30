/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ensolvers.challenge.backend.repository;

import ensolvers.challenge.backend.model.Note;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rauls
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
    Page<Note> findNoteByUser_Username(Pageable pageable, String username);
}
