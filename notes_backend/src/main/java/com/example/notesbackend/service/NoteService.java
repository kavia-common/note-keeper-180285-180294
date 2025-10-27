package com.example.notesbackend.service;

import com.example.notesbackend.dto.NoteDtos.CreateNoteRequest;
import com.example.notesbackend.dto.NoteDtos.NoteResponse;
import com.example.notesbackend.dto.NoteDtos.PatchNoteRequest;
import com.example.notesbackend.dto.NoteDtos.UpdateNoteRequest;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.repository.NoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

/**
 * Service layer for managing notes.
 */
@Service
public class NoteService {

    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    // PUBLIC_INTERFACE
    /**
     * Create a new note.
     * @param req request with title and optional content
     * @return created note response
     */
    @Transactional
    public NoteResponse create(CreateNoteRequest req) {
        Note note = new Note(req.getTitle(), req.getContent());
        Note saved = repository.save(note);
        return toResponse(saved);
    }

    // PUBLIC_INTERFACE
    /**
     * Get a note by id.
     * @param id note id
     * @return note response
     * @throws NoSuchElementException if not found
     */
    @Transactional(readOnly = true)
    public NoteResponse get(Long id) {
        Note note = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Note not found"));
        return toResponse(note);
    }

    // PUBLIC_INTERFACE
    /**
     * List notes with pagination support.
     * @param pageable Pageable parameters
     * @return page of NoteResponse
     */
    @Transactional(readOnly = true)
    public Page<NoteResponse> list(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    // PUBLIC_INTERFACE
    /**
     * Full update of a note.
     * @param id note id
     * @param req update request (title required)
     * @return updated note response
     */
    @Transactional
    public NoteResponse update(Long id, UpdateNoteRequest req) {
        Note note = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Note not found"));
        note.setTitle(req.getTitle());
        note.setContent(req.getContent());
        Note saved = repository.save(note);
        return toResponse(saved);
    }

    // PUBLIC_INTERFACE
    /**
     * Partial update (PATCH) of a note.
     * @param id note id
     * @param req patch request (title/content optional)
     * @return updated note response
     */
    @Transactional
    public NoteResponse patch(Long id, PatchNoteRequest req) {
        Note note = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Note not found"));
        if (req.getTitle() != null) note.setTitle(req.getTitle());
        if (req.getContent() != null) note.setContent(req.getContent());
        Note saved = repository.save(note);
        return toResponse(saved);
    }

    // PUBLIC_INTERFACE
    /**
     * Delete a note by id.
     * @param id note id
     * @throws NoSuchElementException if not found
     */
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Note not found");
        }
        repository.deleteById(id);
    }

    private NoteResponse toResponse(Note note) {
        return new NoteResponse()
                .setId(note.getId())
                .setTitle(note.getTitle())
                .setContent(note.getContent())
                .setCreatedAt(note.getCreatedAt())
                .setUpdatedAt(note.getUpdatedAt());
    }
}
