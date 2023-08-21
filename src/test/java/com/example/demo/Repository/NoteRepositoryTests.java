package com.example.demo.Repository;

import com.example.demo.model.Category;
import com.example.demo.model.Note;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.NoteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class NoteRepositoryTests {


    @Autowired
    private NoteRepository noteRepository;



    @Test
    public void NoteRepository_SaveAll_ReturnsSavedNote() {

        Category category = Category.builder().id(Long.parseLong("1")).category("fiction").build();

        Note note = Note.builder()
                .title("Harry Potter")
                .deskripsi("Mengenai Harry Potter").category(category).build();

        Note savedNote = noteRepository.save(note);

        Assertions.assertThat(savedNote).isNotNull();
        Assertions.assertThat(savedNote.getId());

    }
    @Test
    public void NoteRepository_GetAll_ReturnMoreThanOneNote() {

        Category category = Category.builder().id(Long.parseLong("1")).category("fiction").build();



        Note note = Note.builder()

                .title("Harry Potter")
                .deskripsi("Mengenai Harry Potter")
                .category(category).build();

        Note note2 = Note.builder()

                .title("Harry Potter")
                .deskripsi("Mengenai Harry Potter")
                .category(category).build();

        noteRepository.save(note);
        noteRepository.save(note2);

        List<Note> noteList = noteRepository.findAll();

        Assertions.assertThat(noteList).isNotNull();
        Assertions.assertThat(noteList.size()).isEqualTo(2);

    }
    @Test
    public void NoteRepository_FindById_ReturnNote() {

        Category category = Category.builder().id(Long.parseLong("1")).category("fiction").build();

        Note note = Note.builder()
                .title("Harry Potter")
                .deskripsi("Mengenai Harry Potter").category(category).build();

        noteRepository.save(note);

        Note noteReturn = noteRepository.findById(note.getId()).get();

        Assertions.assertThat(noteReturn).isNotNull();

    }
    @Test
    public void NoteRepository_UpdateNote_ReturnNoteNotNull() {

        Category category = Category.builder().id(Long.parseLong("1")).category("fiction").build();

        Note note = Note.builder()
                .title("Harry Potter")
                .deskripsi("Mengenai Harry Potter").category(category).build();

        Note noteSave = noteRepository.save(note);
        noteSave.setTitle("Elang Biru");
        noteSave.setDeskripsi("Elang Biru Sakti");

        Note updateNote = noteRepository.save(noteSave);


        Assertions.assertThat(updateNote.getTitle()).isNotNull();
        Assertions.assertThat(updateNote.getDeskripsi()).isNotNull();

    }

    @Test
    public void NoteRepository_DeleteNote_ReturnNoteEmpty() {

        Category category = Category.builder().id(Long.parseLong("1")).category("fiction").build();

        Note note = Note.builder()
                .title("Harry Potter")
                .deskripsi("Mengenai Harry Potter").category(category).build();

        noteRepository.save(note);
        noteRepository.deleteById(note.getId());
        Optional<Note> noteReturn = noteRepository.findById(note.getId());

        Assertions.assertThat(noteReturn).isEmpty();


    }
}
