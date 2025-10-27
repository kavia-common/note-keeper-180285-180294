package com.example.notesbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

/**
 * DTO classes for Note API.
 */
public class NoteDtos {

    // PUBLIC_INTERFACE
    /**
     * Request DTO for creating a note.
     * title is required, content is optional.
     */
    public static class CreateNoteRequest {
        @Schema(description = "Title of the note", example = "My first note", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "title is required")
        private String title;

        @Schema(description = "Content of the note", example = "Here are some details...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String content;

        public String getTitle() {
            return title;
        }

        public CreateNoteRequest setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getContent() {
            return content;
        }

        public CreateNoteRequest setContent(String content) {
            this.content = content;
            return this;
        }
    }

    // PUBLIC_INTERFACE
    /**
     * Request DTO for full update of a note.
     * title is required, content optional.
     */
    public static class UpdateNoteRequest {
        @Schema(description = "Title of the note", example = "Updated title", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "title is required")
        private String title;

        @Schema(description = "Content of the note", example = "Updated content", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String content;

        public String getTitle() {
            return title;
        }

        public UpdateNoteRequest setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getContent() {
            return content;
        }

        public UpdateNoteRequest setContent(String content) {
            this.content = content;
            return this;
        }
    }

    // PUBLIC_INTERFACE
    /**
     * Request DTO for partial update (PATCH).
     * title and content are optional; only provided fields are updated.
     */
    public static class PatchNoteRequest {
        @Schema(description = "New title of the note", example = "Patched title")
        private String title;

        @Schema(description = "New content of the note", example = "Patched content")
        private String content;

        public String getTitle() {
            return title;
        }

        public PatchNoteRequest setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getContent() {
            return content;
        }

        public PatchNoteRequest setContent(String content) {
            this.content = content;
            return this;
        }
    }

    // PUBLIC_INTERFACE
    /**
     * Response DTO for note details sent to clients.
     */
    public static class NoteResponse {
        @Schema(description = "Note identifier", example = "1")
        private Long id;

        @Schema(description = "Title of the note", example = "My first note")
        private String title;

        @Schema(description = "Content of the note", example = "Here are some details...")
        private String content;

        @Schema(description = "Creation timestamp (UTC, epoch seconds)", example = "1700000000")
        private Instant createdAt;

        @Schema(description = "Last update timestamp (UTC, epoch seconds)", example = "1700000100")
        private Instant updatedAt;

        public Long getId() {
            return id;
        }

        public NoteResponse setId(Long id) {
            this.id = id;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public NoteResponse setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getContent() {
            return content;
        }

        public NoteResponse setContent(String content) {
            this.content = content;
            return this;
        }

        public Instant getCreatedAt() {
            return createdAt;
        }

        public NoteResponse setCreatedAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Instant getUpdatedAt() {
            return updatedAt;
        }

        public NoteResponse setUpdatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }
    }
}
