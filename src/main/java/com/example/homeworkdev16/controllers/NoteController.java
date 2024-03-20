package com.example.homeworkdev16.controllers;

import com.example.homeworkdev16.service.NoteService;
import com.example.homeworkdev16.model.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/list")
    public ModelAndView listNotes() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("notes", noteService.listAll());
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteNote(@RequestParam("deletedNoteId") Long id) {
        if (id != null) {
            noteService.deleteById(id);
        }
        return listNotes();
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getEditNotePage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Note note = noteService.getById(id);
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editNote(@ModelAttribute Note newNote) {
        if (newNote.getId() != null && newNote.getTitle() != null && newNote.getContent() != null) {
            noteService.update(newNote);
        }
        return listNotes();
    }

    @GetMapping("/create")
    public ModelAndView getCreateNotePage() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("note", new Note());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createNote(@ModelAttribute Note newNote) {
        noteService.add(newNote);
        return listNotes();
    }
}
