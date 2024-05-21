package com.example.data;

import com.example.data.domain.CareProvider;
import com.example.data.domain.HealthIssue;
import com.example.data.domain.Patient;
import com.example.data.services.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private MyService myService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        // Test queries
        Long patientId = 1L;
        List<HealthIssue> healthIssuesForPatient = myService.getAllHealthIssuesForPatient(patientId);
        System.out.println("Health issues for patient with ID " + patientId + ":");
        for (HealthIssue issue : healthIssuesForPatient) {
            System.out.println(issue.getType());
        }

        LocalDate date = LocalDate.now();
        List<Patient> patientsWithEncounterOnDate = myService.getPatientsWithMedicalEncounterOnDate(date);
        System.out.println("\nPatients with a medical encounter on " + date + ":");
        for (Patient patient : patientsWithEncounterOnDate) {
            System.out.println(patient.getName());
        }

        String careProviderName = "Dr. Garcia";
        List<Patient> patientsByCareProvider = myService.getPatientsByCareProviderName(careProviderName);
        System.out.println("\nPatients treated by care provider " + careProviderName + ":");
        for (Patient patient : patientsByCareProvider) {
            System.out.println(patient.getName());
        }

        String healthIssueType = "Fever";
        List<CareProvider> careProvidersByHealthIssue = myService.getCareProvidersByHealthIssueType(healthIssueType);
        System.out.println("\nCare providers treating health issue " + healthIssueType + ":");
        for (CareProvider provider : careProvidersByHealthIssue) {
            System.out.println(provider.getName());
        }
    }
}
