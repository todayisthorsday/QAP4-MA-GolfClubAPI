package com.golfclub.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String email;
    private String phone;
    private String membershipType;
    private LocalDate startDate;
    private int durationInMonths;

    @ManyToMany(mappedBy = "members")
    private Set<Tournament> tournaments = new HashSet<>();
}
