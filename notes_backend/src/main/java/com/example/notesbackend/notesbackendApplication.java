package com.example.notesbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point for the Notes backend.
 * Scans com.example.notesbackend and subpackages for components.
 */
@SpringBootApplication
public class NotesbackendApplication {

    // PUBLIC_INTERFACE
    /**
     * Bootstraps the Spring application.
     * @param args application arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(NotesbackendApplication.class, args);
    }
}
