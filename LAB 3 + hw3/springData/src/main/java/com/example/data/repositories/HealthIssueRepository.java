package com.example.data.repositories;

import com.example.data.domain.CareProvider;
import com.example.data.domain.HealthIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthIssueRepository extends JpaRepository<HealthIssue, Long> {
}
