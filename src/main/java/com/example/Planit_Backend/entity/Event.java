package com.example.Planit_Backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
//    private String eventType;
    private String milestoneType;
    private String status = "pending";
    private String customMilestone;

    @Column(name = "event_date")
    private LocalDate eventDate;

//    @Column(name = "reminder_at")
//    private LocalDateTime reminderAt;
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember familyMember;


}
