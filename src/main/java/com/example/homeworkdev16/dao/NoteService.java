package com.example.homeworkdev16.dao;

import com.example.homeworkdev16.model.Note;
import com.example.homeworkdev16.repository.NoteRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

@Validated
@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository repository;

    public List<Note> listAll() {
        return repository.findAll();
    }

    public Note add(@Valid @NotNull Note note) {
        Random random = new Random();
        long id = random.nextLong();
        while (repository.existsById(id)) {
            id = random.nextLong();
        }
        note.setId(id);
        repository.save(note);
        return note;
    }

    public void deleteById(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("ID not found");
        }
    }

    public void update(@Valid @NotNull Note note) {
        if (repository.existsById(note.getId())) {
            repository.save(note);
        } else {
            throw new IllegalArgumentException("ID not found");
        }
    }

    public Note getById(long id) {
        if (repository.existsById(id)) {
            return repository.findById(id).orElse(null);
        } else {
            throw new IllegalArgumentException("ID not found");
        }
    }
}
