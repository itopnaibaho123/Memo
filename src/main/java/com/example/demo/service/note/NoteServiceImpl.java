package com.example.demo.service.note;

import com.example.demo.Dto.addNote.AddNoteRequest;
import com.example.demo.model.Note;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note findNoteById(String id) {
        return noteRepository.findById(id).get();
    }

    @Override
    public void deleteNoteById(String id) {
        noteRepository.deleteById(id);
    }

    @Override
    public Note saveNote(AddNoteRequest note) {

        Note newNote = Note.builder()
                .title(note.getTitle())
                .deskripsi(note.getDescription())
                .category(categoryRepository
                        .findByCategory(note.getCategory()).get()).build();
        noteRepository.save(newNote);
        return newNote;
    }

    @Override
    public Note updateNote(Note note, AddNoteRequest updatedNote) {
        note.setTitle(updatedNote.getTitle());
        note.setDeskripsi(updatedNote.getDescription());
        note.setCategory(categoryRepository.findByCategory(updatedNote.getCategory()).get());
        noteRepository.save(note);
        return note;
    }
}
