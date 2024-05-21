package com.example.data.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    public Patient(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "patient")
    private List<MedicalEncounter> medicalEncounters = new ArrayList<>();

    // Constructors, getters, and setters
    public List<MedicalEncounter> getMedicalEncounters() {
        return medicalEncounters;
    }
    public void setMedicalEncounters(List<MedicalEncounter> medicalEncounters) {
        this.medicalEncounters = medicalEncounters;
    }

}
