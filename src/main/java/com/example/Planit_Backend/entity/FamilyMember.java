package com.example.Planit_Backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "family_members")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String relation;
    private LocalDate dob;
    private String gender;

    @ElementCollection
    private List<String> interests;

    @ElementCollection
    private List<String> hobbies;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
