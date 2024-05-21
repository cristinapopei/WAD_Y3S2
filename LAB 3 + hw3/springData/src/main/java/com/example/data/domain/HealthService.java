package com.example.data.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class HealthService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String type;
    @ManyToOne
    private HealthIssue healthIssue;

    public HealthService(String type,  MedicalEncounter medicalEncounter, HealthIssue healthIssue, CareProvider careProvider) {
        this.type = type;
        this.healthIssue = healthIssue;
        this.careProvider = careProvider;
        this.medicalEncounter = medicalEncounter;
    }

    @ManyToOne
    private CareProvider careProvider;
    @ManyToOne
    private MedicalEncounter medicalEncounter;
    public HealthService(String description,String type) {
        this.description=description;
        this.type = type;
    }

}
