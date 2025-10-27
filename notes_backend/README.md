# Notes Backend (Spring Boot)

A simple RESTful API to create, read, update, and delete notes.

Tech stack:
- Spring Boot 3
- Spring Web, Spring Data JPA
- H2 in-memory database
- Bean Validation
- springdoc-openapi (Swagger UI)

How to run:
- Java 17+ required
- From this directory: ./gradlew bootRun
- The API will start on http://localhost:3001

Key URLs:
- API base: http://localhost:3001/api/notes
- OpenAPI JSON: http://localhost:3001/openapi.json
- Swagger UI: http://localhost:3001/swagger-ui.html
- Convenience redirect to Swagger UI (preserves scheme/host/port): http://localhost:3001/docs
- H2 Console: http://localhost:3001/h2-console (JDBC URL: jdbc:h2:mem:notesdb, user: sa, no password)

Endpoints:
/api/notes
- POST /api/notes: create note (201)
- GET /api/notes: list notes (200, paginated with ?page=0&size=20&sort=createdAt,desc)
- GET /api/notes/{id}: get one (200 or 404)
- PUT /api/notes/{id}: full update (200 or 404)
- PATCH /api/notes/{id}: partial update (200 or 404)
- DELETE /api/notes/{id}: delete (204 or 404)

Validation and errors:
- Invalid inputs return 400 with fieldErrors
- Missing note IDs return 404

CORS:
- All origins allowed (development)

Notes:
- Data is in-memory (reset on restart). Adjust JPA/H2 config as needed for persistence.

Troubleshooting:
- If /openapi.json returns 404, ensure the application started successfully and that `springdoc-openapi-starter-webmvc-ui` is on the classpath (see build.gradle).
- In proxied environments, access Swagger UI using /docs to preserve the original scheme/host when redirecting.
