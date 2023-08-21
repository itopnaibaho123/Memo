package com.example.demo.service.note;

import com.example.demo.Dto.addNote.AddNoteRequest;
import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface NoteService {

    List<Note> findAll();
    Note findNoteById(String id);
    void deleteNoteById(String id);
    Note saveNote(AddNoteRequest note);
    Note updateNote(Note note, AddNoteRequest updateNote);
}
