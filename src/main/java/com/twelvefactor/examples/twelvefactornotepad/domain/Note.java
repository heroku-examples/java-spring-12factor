package com.twelvefactor.examples.twelvefactornotepad.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notes")
@Schema(description = "Represents a user note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the Note.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(nullable = false)
    @Schema(
            description = "Content of the note.",
            example = "Remember to buy milk.",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Column(columnDefinition = "TEXT")
    @Schema(
            description = "Content of the note.",
            example = "Remember to buy milk.",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

    private String color;

    @Column(name = "position_x")
    private Integer positionX;

    @Column(name = "position_y")
    private Integer positionY;

    @Column(updatable = false)
    @CreationTimestamp
    @Schema(
            description = "Timestamp of when the note was created.",
            example = "2023-05-14T10:00:00Z",
            accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Schema(
            description = "Timestamp of when the note was last updated.",
            example = "2023-05-14T12:30:00Z",
            accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;
}
