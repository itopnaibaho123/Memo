package com.example.demo.restcontroller;


import com.example.demo.Dto.addNote.AddNoteRequest;
import com.example.demo.model.Note;
import com.example.demo.service.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController

@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping(value = "")
    private List<Note> getAllNotes() {
        try {
            return noteService.findAll();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }
    }

    @GetMapping(value = "/{id}")
    private Note getNote(@PathVariable("id") String id) {
        try {
            return noteService.findNoteById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }
    }

    @DeleteMapping(value = "/{id}")
    private void deleteNote(@PathVariable("id") String id) {
        try {
            noteService.deleteNoteById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }
    }

    @PostMapping(value = "")
    private Note saveNotes(@RequestBody AddNoteRequest note, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field.");
        }
        try {
            return noteService.saveNote(note);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }
    }

    @PutMapping(value = "/{id}")
    private Note updateNote(@PathVariable("id") String id, @RequestBody AddNoteRequest updatedNote, BindingResult bindingResult) {
        Note note;
        try {
            note = noteService.findNoteById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }

        try {
            return noteService.updateNote(note, updatedNote);
        } catch (UnsupportedOperationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Note can't be update");
        }
    }
}
