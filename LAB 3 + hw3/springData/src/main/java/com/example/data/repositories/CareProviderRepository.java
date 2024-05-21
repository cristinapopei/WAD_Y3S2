package com.example.data.repositories;

import com.example.data.domain.CareProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareProviderRepository extends JpaRepository<CareProvider, Long> {
}
