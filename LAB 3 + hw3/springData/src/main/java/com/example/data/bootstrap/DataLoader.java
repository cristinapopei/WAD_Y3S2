package com.example.data.bootstrap;

import com.example.data.domain.*;
import com.example.data.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final CareProviderRepository careProviderRepository;
    private final HealthIssueRepository healthIssueRepository;
    private final HealthServiceRepository healthServiceRepository;
    private final MedicalEncounterRepository medicalEncounterRepository;
    private final PatientRepository patientRepository;

    public DataLoader(CareProviderRepository careProviderRepository,
                      HealthIssueRepository healthIssueRepository,
                      HealthServiceRepository healthServiceRepository,
                      MedicalEncounterRepository medicalEncounterRepository,
                      PatientRepository patientRepository) {
        this.careProviderRepository = careProviderRepository;
        this.healthIssueRepository = healthIssueRepository;
        this.healthServiceRepository = healthServiceRepository;
        this.medicalEncounterRepository = medicalEncounterRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        Patient patient1 = new Patient("John Doe");
        Patient patient2 = new Patient("Jane Smith");
        Patient patient3 = new Patient("Alice Johnson");
        Patient patient4 = new Patient("Michael Brown");
        Patient patient5 = new Patient("Emily Wilson");
        patientRepository.saveAll(List.of(patient1, patient2,patient3, patient4, patient5));

        HealthIssue issue1 = new HealthIssue("Fever");
        HealthIssue issue2 = new HealthIssue("Headache");
        HealthIssue issue3 = new HealthIssue("Stomach Pain");
        HealthIssue issue4 = new HealthIssue("Backache");
        HealthIssue issue5 = new HealthIssue("Allergy");
        healthIssueRepository.saveAll(List.of(issue1, issue2, issue3, issue4, issue5));


        HealthService service1 = new HealthService("Checkup", "General Checkup");
        HealthService service2 = new HealthService("Prescription", "Medication Prescription");
        HealthService service3 = new HealthService("X-ray", "Radiology");
        HealthService service4 = new HealthService("Blood Test", "Lab Test");
        HealthService service5 = new HealthService("Physical Therapy", "Rehabilitation");
        healthServiceRepository.saveAll(List.of(service1, service2,service3, service4, service5));


        CareProvider provider1 = new CareProvider("Dr. Smith", "General Physician");
        CareProvider provider2 = new CareProvider("Dr. Johnson", "Neurologist");
        CareProvider provider3 = new CareProvider("Dr. Martinez", "Orthopedic Surgeon");
        CareProvider provider4 = new CareProvider("Dr. Lee", "Cardiologist");
        CareProvider provider5 = new CareProvider("Dr. Garcia", "Dermatologist");
        careProviderRepository.saveAll(List.of(provider1, provider2,provider3, provider4, provider5));

        MedicalEncounter encounter1 = new MedicalEncounter(LocalDate.now());
        MedicalEncounter encounter2 = new MedicalEncounter(LocalDate.now().minusDays(2));
        MedicalEncounter encounter3 = new MedicalEncounter(LocalDate.now().minusDays(3));
        MedicalEncounter encounter4 = new MedicalEncounter(LocalDate.now().minusDays(4));
        MedicalEncounter encounter5 = new MedicalEncounter(LocalDate.now().minusDays(5));

        encounter1.setPatient(patient1);
        encounter1.setCareProvider(provider5);
        encounter1.setHealthService(service1);

        encounter2.setPatient(patient2);
        encounter2.setHealthService(service2);
        encounter2.setCareProvider(provider4);

        encounter3.setPatient(patient3);
        encounter3.setHealthService(service3);
        encounter3.setCareProvider(provider3);

        encounter4.setPatient(patient4);
        encounter4.setHealthService(service4);
        encounter4.setCareProvider(provider4);

        encounter5.setPatient(patient5);
        encounter5.setHealthService(service5);
        encounter5.setCareProvider(provider5);

        medicalEncounterRepository.saveAll(List.of(encounter1, encounter2,encounter3, encounter4, encounter5));

        service1.setCareProvider(provider1);
        service1.setHealthIssue(issue1);
        service1.setMedicalEncounter(encounter1);

        service2.setCareProvider(provider2);
        service2.setHealthIssue(issue2);
        service2.setMedicalEncounter(encounter2);

        service3.setCareProvider(provider1);
        service3.setHealthIssue(issue3);
        service3.setMedicalEncounter(encounter3);

        service4.setCareProvider(provider2);
        service4.setHealthIssue(issue1);
        service4.setMedicalEncounter(encounter4);
        service5.setCareProvider(provider1);
        service5.setHealthIssue(issue2);
        service5.setMedicalEncounter(encounter5);

        List<HealthService> healthServices = List.of(service1, service2, service3, service4, service5);
        healthServiceRepository.saveAll(healthServices);


    }
}
