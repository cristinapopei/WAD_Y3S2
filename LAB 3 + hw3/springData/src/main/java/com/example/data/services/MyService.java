package com.example.data.services;

import com.example.data.domain.CareProvider;
import com.example.data.domain.HealthIssue;
import com.example.data.domain.Patient;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MyService {

    private final EntityManager entityManager;

    @Autowired
    public MyService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<HealthIssue> getAllHealthIssuesForPatient(Long patientId) {
        return entityManager.createQuery(
                        "SELECT DISTINCT hi FROM HealthIssue hi " +
                                "JOIN hi.healthServices hs " +
                                "JOIN hs.medicalEncounter me " +
                                "WHERE me.patient.id = :patientId", HealthIssue.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }

    public List<Patient> getPatientsWithMedicalEncounterOnDate(LocalDate date) {
        return entityManager.createQuery(
                        "SELECT DISTINCT p FROM Patient p " +
                                "JOIN p.medicalEncounters me " +
                                "WHERE me.date = :date", Patient.class)
                .setParameter("date", date)
                .getResultList();
    }

    public List<Patient> getPatientsByCareProviderName(String careProviderName) {
        return entityManager.createQuery(
                        "SELECT DISTINCT p FROM Patient p " +
                                "JOIN p.medicalEncounters me " +
                                "JOIN me.careProvider cp " +
                                "WHERE cp.name = :careProviderName", Patient.class)
                .setParameter("careProviderName", careProviderName)
                .getResultList();
    }

    public List<CareProvider> getCareProvidersByHealthIssueType(String healthIssueType) {
        return entityManager.createQuery(
                        "SELECT DISTINCT cp FROM CareProvider cp " +
                                "JOIN cp.healthServices hs " +
                                "JOIN hs.healthIssue hi " +
                                "WHERE hi.type = :healthIssueType", CareProvider.class)
                .setParameter("healthIssueType", healthIssueType)
                .getResultList();
    }
}
