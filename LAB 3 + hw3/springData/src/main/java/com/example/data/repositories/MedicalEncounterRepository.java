package com.example.data.repositories;

import com.example.data.domain.CareProvider;
import com.example.data.domain.MedicalEncounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalEncounterRepository extends JpaRepository<MedicalEncounter, Long> {
}
