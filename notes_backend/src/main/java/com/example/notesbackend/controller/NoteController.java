package com.example.notesbackend.controller;

import com.example.notesbackend.dto.NoteDtos.CreateNoteRequest;
import com.example.notesbackend.dto.NoteDtos.NoteResponse;
import com.example.notesbackend.dto.NoteDtos.PatchNoteRequest;
import com.example.notesbackend.dto.NoteDtos.UpdateNoteRequest;
import com.example.notesbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller providing CRUD operations for notes under /api/notes.
 */
@RestController
@RequestMapping("/api/notes")
@Tag(name = "Notes", description = "CRUD operations for Notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "Create a note", description = "Creates a new note with a required title and optional content")
    @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(schema = @Schema(implementation = NoteResponse.class)))
    @PostMapping
    public ResponseEntity<NoteResponse> create(@Valid @RequestBody CreateNoteRequest req) {
        NoteResponse created = service.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "List notes", description = "Returns a paginated list of notes")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public ResponseEntity<Page<NoteResponse>> list(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.list(pageable));
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "Get a note", description = "Returns a single note by id")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = NoteResponse.class)))
    @ApiResponse(responseCode = "404", description = "Not Found")
    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "Update a note", description = "Full update of a note; title required")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = NoteResponse.class)))
    @ApiResponse(responseCode = "404", description = "Not Found")
    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateNoteRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "Patch a note", description = "Partial update; only provided fields are modified")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = NoteResponse.class)))
    @ApiResponse(responseCode = "404", description = "Not Found")
    @PatchMapping("/{id}")
    public ResponseEntity<NoteResponse> patch(@PathVariable Long id, @RequestBody PatchNoteRequest req) {
        return ResponseEntity.ok(service.patch(id, req));
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "Delete a note", description = "Deletes a note by id")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
