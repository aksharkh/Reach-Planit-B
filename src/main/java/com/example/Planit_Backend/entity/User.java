package com.example.Planit_Backend.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Column(name = "user_name")
    private String userName;

    @Column(length = 50)
    private String provider;

    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();


}
