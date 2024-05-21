package com.example.data.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class MedicalEncounter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @ManyToOne
    private Patient patient;

    @OneToOne
    private HealthService healthService;

    @OneToOne
    private CareProvider careProvider;

    public MedicalEncounter(LocalDate date) {
        this.date = date;
    }

    public HealthService getHealthService() {
        return healthService;
    }
    public void setHealthService(HealthService healthService) {
        this.healthService = healthService;
    }
}
