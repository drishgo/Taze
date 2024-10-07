package com.example.Taze.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data // Automatically generates getters, setters, and other utility methods
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false) // Adjusted to match database schema
    private String phoneNumber;

    @Column(nullable = false)
    private String password; // For authentication

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // Store when the user was created

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt; // Store when the user details were last updated

    // Constructor to set timestamps
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
