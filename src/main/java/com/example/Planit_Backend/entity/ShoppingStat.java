package com.example.Planit_Backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "shopping-stata")
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingStat {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private Double amount;
    private String period;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
